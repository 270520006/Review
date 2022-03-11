package classloader;

import pojo.Student;

/**
 * @author zsp
 * @version 1.0
 * @date 2022/3/11 10:37
 */
public class ClassLoaderDemo {
    public static void main(String[] args) {
        Student student = new Student();
        //获取加载器：sun.misc.Launcher$AppClassLoader@18b4aac2
        ClassLoader loader = student.getClass().getClassLoader();
        System.out.println(loader);
        //获取父类加载器：sun.misc.Launcher$ExtClassLoader@1540e19d
        ClassLoader parent = loader.getParent();
        System.out.println(parent);
        //获取父类的父类加载器:null,推测可能bootstrap加载器是c底层，无法获取。
        ClassLoader parentParent = parent.getParent();
        System.out.println(parentParent);
    }
}
