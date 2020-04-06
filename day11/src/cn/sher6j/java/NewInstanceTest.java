package cn.sher6j.java;

import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
 * 通过反射创建对应的运行时类的对象
 *
 * @author sher6j
 * @create 2020-04-04-20:21
 */
public class NewInstanceTest {

    @Test
    public void test1() throws IllegalAccessException, InstantiationException {
        Class<Person> personClass = Person.class;
        /*
        newInstance()：调用此方法创建对应的运行时类的对象，内部调用运行时类的空参构造器

        要想此方法正常的创建运行时类的对象，要求：
        1. 运行时类必须提供空参构造器
        2. 空参构造器的访问权限得够，通常，设置为public

        在javabean中要求提供一个public的空参构造器。原因：
        1. 便于通过反射，创建运行时类的对象
        2. 便于子类继承此运行时类时，默认调用super()时，保证父类有此构造器
         */
        Person person = personClass.newInstance();  //@Deprecated(since="9")
        //xxxClass.newInstance()方法过时，被xxxClass.getDeclaredConstructor().newInstance()取代
        Person person1 = null;
        try {
            person1 = personClass.getDeclaredConstructor().newInstance();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        System.out.println(person);
        System.out.println(person1);
    }

    //体会反射的动态性
    @Test
    public void test2() {
        int num = new Random().nextInt(3); //0,1,2
        String classPath = "";
        switch (num) {
            case 0:
                classPath = "java.util.Date";
                break;
            case 1:
                classPath = "java.sql.Date"; //抛异常NoSuchMethodException，没有空参构造器
                break;
            case 2:
                classPath = "cn.sher6j.java.Person";
                break;
        }
        try {
            Object obj = getInstance(classPath);
            System.out.println(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /*
    创建一个指定类的对象
    classPath：指定类的全类名
     */
    public Object getInstance(String classPath) throws Exception{
        Class<?> aClass = Class.forName(classPath);

        return aClass.getDeclaredConstructor().newInstance();
    }
}
