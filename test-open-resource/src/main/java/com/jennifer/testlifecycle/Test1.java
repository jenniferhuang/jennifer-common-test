package com.jennifer.testlifecycle;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: com.jennifer.huang
 * Date: 2/10/2017
 */
public class Test1 {


    /**
     *  如果一个类被直接引用，就会触发类的初始化。在java中，直接引用的情况有：

     通过new关键字实例化对象、读取或设置类的静态变量、调用类的静态方法。
     通过反射方式执行以上三种行为。
     初始化子类的时候，会触发父类的初始化。
     作为程序入口直接运行时（也就是直接调用main方法）。
     */


    static {
        System.out.println("Program entry, class initialization");
    }

        /**
         * 主动引用引起类的初始化的第四种情况就是运行Test1的main方法时
         * 导致Test1初始化，这一点很好理解，就不特别演示了。
         * 本代码演示了前三种情况，以下代码都会引起InitClass的初始化，
         * 但由于初始化只会进行一次，运行时请将注解去掉，依次运行查看结果。
         * @param args
         * @throws Exception
         */
        public static void main(String[] args) throws Exception{
//              主动引用引起类的初始化一: new对象、读取或设置类的静态变量、调用类的静态方法。
//              new InitClass();
//              InitClass.a = "";
//              String a = InitClass.a;
//              InitClass.method();

            //  主动引用引起类的初始化二：通过反射实例化对象、读取或设置类的静态变量、调用类的静态方法。
//              Class cls = InitClass.class;
//              cls.newInstance();
//
//              Field f = cls.getDeclaredField("a");
//              f.get(null);
//              f.set(null, "s");
//
//              Method md = cls.getDeclaredMethod("method");
//              md.invoke(null, null);

            //  主动引用引起类的初始化三：实例化子类，引起父类初始化。
              new SubInitClass();

        }
}
