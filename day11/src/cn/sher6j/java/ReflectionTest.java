package cn.sher6j.java;

import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author sher6j
 * @create 2020-04-04-14:46
 */
public class ReflectionTest {

    //反射之前，对于Person的操作
    @Test
    public void test1() {

        //1. 创建Person类的对象
        Person p1 = new Person("James", 34);
        //2. 通过对象调用其内部的属性、方法
        p1.age = 10;
        System.out.println(p1.toString());

        p1.show();

        //在Person类的外部，不可以通过Person类的对象调用其内部私有结构
        //比如name, showNation()以及私有构造器
    }

    //反射以后，对于Person的操作
    @Test
    public void test2() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Class personClass = Person.class;
        //1.通过反射，创建Person类的对象
        Constructor cons = personClass.getConstructor(String.class, int.class);
        Object obj = cons.newInstance("James", 34);
        Person p = (Person) obj;
        System.out.println(p.toString());

        //2.通过反射，调用对象的指定属性和方法
        //调用属性
        Field age = personClass.getDeclaredField("age");
        age.set(p, 10);
        System.out.println(p.toString());
        //调用方法
        Method show = personClass.getDeclaredMethod("show");
        show.invoke(p);

        //3.通过反射，可以调用Person类的私有结构的。比如：私有的构造器、方法、属性
        //调用私有的构造器
        Constructor cons1 = personClass.getDeclaredConstructor(String.class);
        cons1.setAccessible(true);
        Person jack = (Person) cons1.newInstance("Jack");
        System.out.println(jack);
        //调用私有的属性
        Field name = personClass.getDeclaredField("name");
        name.setAccessible(true);
        name.set(jack, "Tom");
        System.out.println(jack);
        //调用私有的方法
        Method showNation = personClass.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String nation = (String) showNation.invoke(jack, "中国");//相当于String nation = p1.showNation("中国")
        System.out.println(nation);
    }

    //疑问1：通过直接new的方式或反射的方式都可以调用公共的结构，开发中到底用哪个？
    //建议：直接new的方式。
    //什么时候会使用：反射的方式。反射的特征：动态性。
    //疑问2：反射机制与面向对象中的封装性是不是矛盾的？如何看待两个技术？
    //不矛盾。

    /*
    关于java.lang.Class类的理解
    1. 类的加载过程：
    程序经过javac.exe命令以后，会生成一个或多个字节码文件(.class结尾)，
    接着我们使用java.exe命令对某个字节码文件进行解释运行，相当于将某个字节码文件加载到内存中，此过程称为类的加载。
    加载到内存中的类称为运行时类，此运行时类，就作为Class的一个实例

    2. 换句话说，Class的实例就对应着一个运行时类
    3. 加载到内存中的运行时类，会缓存一定的时间，在此时间之内，我们可以通过不同的方式获取此运行时类
     */

    //万事万物皆对象？ File、URL、前端、数据库操作、即使类本身也是对象

    /*
    获取Class的实例的方式（前三种方式需要掌握）
     */
    @Test
    public void test3() {
        //方式一：调用运行时类的属性
        Class<Person> personClass = Person.class;
        System.out.println(personClass);

        //方式二：通过运行时类的对象
        Person p1 = new Person();
        Class<? extends Person> p1Class = p1.getClass();
        System.out.println(p1Class);

        //方式三：调用Class的静态方法：forName(String classPath)
        Class<?> aClass = null;
        try {
            aClass = Class.forName("cn.sher6j.java.Person");//类的全类名
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(aClass);

        System.out.println(personClass == aClass);
        System.out.println(personClass == p1Class);

        //方式四：类的加载器ClassLoader
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class<?> aClass1 = null;
        try {
            aClass1 = classLoader.loadClass("cn.sher6j.java.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
        }
        System.out.println(aClass1);

        System.out.println(aClass1 == aClass);

    }

    //Class实例可以是哪些结构
    @Test
    public void test4() {
        Class c1 = Object.class;
        Class c2 = Comparable.class;
        Class c3 = String[].class;
        Class c4 = int[][].class;
        Class c5 = ElementType.class;
        Class c6 = Override.class;
        Class c7 = int.class;
        Class c8 = void.class;
        Class c9 = Class.class;

        int[] a = new int[10];
        int[] b = new int[100];
        Class c10 = a.getClass();
        Class c11 = b.getClass();
        // 只要数组的元素类型与维度一样，就是同一个Class
        System.out.println(c10 == c11);
    }
}
