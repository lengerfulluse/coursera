package basic;

/**
 * Test java class initialization sequence.
 */

public class ClassInitializeSequence {

    String name; // 声明变量name
    String sex; // 声明变量sex
    static int age = 1;// 声明静态变量age

    // 构造方法
    public   ClassInitializeSequence() {
        System.out.println("通过构造方法初始化name");
        name = "tom";
    }

    // 初始化块
    {
        System.out.println("通过初始化块初始化sex");
        sex = "男";
    }

    // 静态初始化块
    static  {
        age = 20;
        System.out.println("通过静态初始化块初始化age" + age);
    }

    public void show() {
        System.out.println("姓名：" + name + "，性别：" + sex + "，年龄：" + age);
    }

    public static void main(String[] args) {

        // 创建对象
        ClassInitializeSequence hello = new ClassInitializeSequence();
        // 调用对象的show方法
        hello.show();
    }
}
