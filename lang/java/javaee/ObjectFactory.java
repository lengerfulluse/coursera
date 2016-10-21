
package me.hengwei.t.tutorial.javaee;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the me.hengwei.t.tutorial.javaee package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _HelloWebService_QNAME = new QName("http://javaee.tutorial.t.hengwei.me/", "HelloWebService");
    private final static QName _HelloWebServiceResponse_QNAME = new QName("http://javaee.tutorial.t.hengwei.me/", "HelloWebServiceResponse");
    private final static QName _SayHello_QNAME = new QName("http://javaee.tutorial.t.hengwei.me/", "sayHello");
    private final static QName _SayHelloResponse_QNAME = new QName("http://javaee.tutorial.t.hengwei.me/", "sayHelloResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: me.hengwei.t.tutorial.javaee
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link HelloWebService_Type }
     * 
     */
    public HelloWebService_Type createHelloWebService_Type() {
        return new HelloWebService_Type();
    }

    /**
     * Create an instance of {@link HelloWebServiceResponse }
     * 
     */
    public HelloWebServiceResponse createHelloWebServiceResponse() {
        return new HelloWebServiceResponse();
    }

    /**
     * Create an instance of {@link SayHello }
     * 
     */
    public SayHello createSayHello() {
        return new SayHello();
    }

    /**
     * Create an instance of {@link SayHelloResponse }
     * 
     */
    public SayHelloResponse createSayHelloResponse() {
        return new SayHelloResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloWebService_Type }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://javaee.tutorial.t.hengwei.me/", name = "HelloWebService")
    public JAXBElement<HelloWebService_Type> createHelloWebService(HelloWebService_Type value) {
        return new JAXBElement<HelloWebService_Type>(_HelloWebService_QNAME, HelloWebService_Type.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloWebServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://javaee.tutorial.t.hengwei.me/", name = "HelloWebServiceResponse")
    public JAXBElement<HelloWebServiceResponse> createHelloWebServiceResponse(HelloWebServiceResponse value) {
        return new JAXBElement<HelloWebServiceResponse>(_HelloWebServiceResponse_QNAME, HelloWebServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://javaee.tutorial.t.hengwei.me/", name = "sayHello")
    public JAXBElement<SayHello> createSayHello(SayHello value) {
        return new JAXBElement<SayHello>(_SayHello_QNAME, SayHello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SayHelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://javaee.tutorial.t.hengwei.me/", name = "sayHelloResponse")
    public JAXBElement<SayHelloResponse> createSayHelloResponse(SayHelloResponse value) {
        return new JAXBElement<SayHelloResponse>(_SayHelloResponse_QNAME, SayHelloResponse.class, null, value);
    }

}
