package cn.sher6j.java2;

import cn.sher6j.java1.Person;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 调用运行时类中指定的结构：属性、方法、构造器
 *
 * @author sher6j
 * @create 2020-04-05-11:01
 */
public class ReflectionTest {

    /*
    不需掌握
     */
    @Test
    public void testField() throws Exception {
        Class<Person> personClass = Person.class;

        //创建运行时类的对象
        Person p = personClass.getDeclaredConstructor().newInstance();
        //获取指定的属性:要求该属性声明为public
        Field id = personClass.getField("id"); //属性通常不是public，故通常不采用此方法
        //设置当前属性的值
        id.set(p, 101); //参数1：指明设置哪个对象的属性。参数2：将此属性值设置为多少
        //获取当前属性的值
        int pid = (int) id.get(p); //参数：获取哪个对象的当前属性值
        System.out.println(pid);
    }

    /*
    如何操作运行时类中的指定的属性 ---- 需要掌握
     */
    @Test
    public void testField1() throws Exception {
        Class<Person> personClass = Person.class;

        Person p = personClass.getDeclaredConstructor().newInstance();
        //1. getDeclaredField(String fieldName) 获取运行时类中指定变量名的属性
        Field name = personClass.getDeclaredField("name");
        //2. 保证当前属性可访问
        name.setAccessible(true);
        //3. 设置指定对象的此属性值
        name.set(p, "James");
        System.out.println(name.get(p));

    }

    /*
    如何操作运行时类中的指定的方法 ---- 需要掌握
     */
    @Test
    public void testMethod() throws Exception {
        Class<Person> personClass = Person.class;

        //创建运行时类的对象
        Person p = personClass.getDeclaredConstructor().newInstance();

        //1. 获取指定的某个方法
        /*
        getDeclaredMethod() 参数1：指明获取的方法的名称
                            参数2：该名方法或许有很多(重载)，指明参数列表
         */
        Method show = personClass.getDeclaredMethod("show", String.class);
        //2. 保证当前方法可访问
        show.setAccessible(true);
        //3. 调用invoke执行方法
        /*
        invoke() 参数1：方法的调用者
                 参数2：给方法形参赋值的实参
        invoke()的返回值即对应类中该方法应该返回的返回值
         */
        Object returnValue = show.invoke(p, "CHN");
        System.out.println(returnValue);
        System.out.println("--------");

        //private static void showDesc()
        Method showDesc = personClass.getDeclaredMethod("showDesc");
        showDesc.setAccessible(true);
        //如果调用的运行时类的方法没有返回值，invoke返回null
        Object returnVal = showDesc.invoke(Person.class);
        System.out.println(returnVal);
    }

    /*
    如何调用运行时类中的指定的构造器
     */
    @Test
    public void testConstructor() throws Exception {
        Class<Person> personClass = Person.class;

        /*
        1.获取指定的构造器
        getDeclaredConstructor() 参数：指明构造器的参数列表
         */
        Constructor<Person> cons = personClass.getDeclaredConstructor(String.class);
        //2.保证可访问
        cons.setAccessible(true);
        //3.调用构造器创建运行时类的对象
        Person p = cons.newInstance("James");
        System.out.println(p);
    }
}
