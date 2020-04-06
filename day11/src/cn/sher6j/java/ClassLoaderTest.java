package cn.sher6j.java;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 了解类的加载器
 * @author sher6j
 * @create 2020-04-04-19:58
 */
public class ClassLoaderTest {

    //java9模块化之后，对classloader有所改造，其中一点就是将ext classloader改为platform classloader
    @Test
    public void test1() {
        //对于自定义类，使用系统类加载器进行加载
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader(); //获取类的加载器
        System.out.println(classLoader);
        //调用系统类加载的getParent()：获取扩展类加载器
        ClassLoader parent = classLoader.getParent();
        System.out.println(parent);
        //调用扩展类加载器的getParent()：无法获取引导类加载器
        //引导类加载器主要负责加载Java的核心类库，无法加载自定义类
        ClassLoader parent1 = parent.getParent();
        System.out.println(parent1);

        ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println(classLoader1);
    }

    /*
    Properties：用来读取配置文件
     */
    @Test
    public void test2() throws Exception {
        Properties pros = new Properties();

        //读取配置文件的方式一：
        //此时的文件默认在当前的module下
//        FileInputStream fis = new FileInputStream("jdbc.properties");
//        pros.load(fis);

        //读取配置文件的方式二：使用ClassLoader
        //此时的文件默认在当前module的src下
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("jdbc.properties");
        pros.load(is);

        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        System.out.println("user = " + user + ", password = " + password);
    }
}
