package com.jennifer.classloader;

/**
 * Created by IntelliJ IDEA.
 * User: com.jennifer.huang
 * Date: 3/24/2017
 */
public class ClassLoaderTest {


    public static void main(String[] args) {
        try {
            /*ClassLoader loader = ClassLoaderTest.class.getClassLoader();  //获得ClassLoaderTest这个类的类加载器
            while(loader != null) {
                System.out.println(loader);
                loader = loader.getParent();    //获得父加载器的引用
            }
            System.out.println(loader);*/


            String rootUrl = "http://localhost:8080/example/WEB-INF/classes";
            NetworkClassLoader networkClassLoader = new NetworkClassLoader(rootUrl);
            String classname = "HelloWorldExample";
            Class clazz = networkClassLoader.loadClass(classname);
            System.out.println(clazz.getClassLoader());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }















    public void test() {
        ClassLoader loader = ClassLoaderTest.class.getClassLoader();    //获得加载ClassLoaderTest.class这个类的类加载器
        while(loader != null) {
            System.out.println(loader);
            loader = loader.getParent();    //获得父类加载器的引用
        }
        System.out.println(loader);
    }
}

//

/**
 * 首先由最顶层的类加载器Bootstrap ClassLoader试图加载，
 * 如果没加载到，则把任务转交给Extension ClassLoader试图加载，
 * 如果也没加载到，则转交给App ClassLoader 进行加载，
 * 如果它也没有加载得到的话，则返回给委托的发起者，由它到指定的文件系统或网络等URL中加载该类。
 * 如果它们都没有加载到这个类时，则抛出ClassNotFoundException异常。
 * 否则将这个找到的类生成一个类的定义，并将它加载到内存当中，最后返回这个类在内存中的Class实例对象。
 */
