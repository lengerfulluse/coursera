package me.hengwei.t.java.compile;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.*;
import javax.lang.model.util.ElementScanner6;
import javax.tools.Diagnostic;
import java.lang.reflect.Method;

/**
 * Created by hengwei on 21/11/2016.
 */
public class NameChecker {
    private Messager messager;
    NameCheckScanner nameCheckScanner=new NameCheckScanner();

    NameChecker(ProcessingEnvironment processingEnvironment) {
        this.messager = processingEnvironment.getMessager();
    }

    public void checkNames(Element element){
        nameCheckScanner.scan(element);
    }

    private class NameCheckScanner extends ElementScanner6<Void, Void> {

        public Void visitPackage(PackageElement e, Void p) {
            return scan(e.getEnclosedElements(), p);
        }

        /**
         * check class name symbol.
         */
        @Override
        public Void visitType(TypeElement e, Void p) {
            scan(e.getTypeParameters(), p);
            checkCamelCase(e, true);
            super.visitType(e, p);
            return null;
        }

        /**
         * check variable symbol.
         */
        public Void visitVariable(VariableElement e, Void p) {
            checkAllCaps(e);
            return null;
        }

        /**
         * check method symbol.
         */
        public Void visitExecutable(ExecutableElement e, Void p) {

            if(e.getKind() == ElementKind.METHOD) {
                Name name = e.getSimpleName();
                if(name.contentEquals(e.getEnclosingElement().getSimpleName())) {
                    messager.printMessage(Diagnostic.Kind.WARNING, "method name duplicated with class name");
                }
                checkCamelCase(e, false);
                super.visitExecutable(e, p);
            }
            return null;
        }

        private void checkCamelCase(Element e, boolean initialCaps){

        }

        private void checkAllCaps(Element e){

        }
    }
}
