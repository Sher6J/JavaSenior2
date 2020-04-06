package cn.sher6j.java2;

import cn.sher6j.java1.Person;
import org.junit.Test;

import javax.swing.border.CompoundBorder;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * 获取当前运行时类的属性结构
 *
 * @author sher6j
 * @create 2020-04-04-21:06
 */
public class FieldTest {

    @Test
    public void test1() {
        Class<Person> personClass = Person.class;

        //获取属性结构
        //getFields()：获取当前运行时类及其父类中声明为public访问权限的属性
        Field[] fields = personClass.getFields();
        for (Field field : fields) {
            System.out.println(field);
        }

        System.out.println("----------");

        //getDeclaredFields()：获取当前运行时类中声明的所有属性（不包含父类中声明的属性）
        Field[] declaredFields = personClass.getDeclaredFields();
        for (Field field : declaredFields) {
            System.out.println(field);
        }
    }

    //获取属性结构：权限修饰符，数据类型，变量名，
    @Test
    public void test2() {
        Class<Person> personClass = Person.class;
        Field[] declaredFields = personClass.getDeclaredFields();

        for (Field field : declaredFields) {
            //1.权限修饰符
            int modifiers = field.getModifiers();
            System.out.print(modifiers + "" + Modifier.toString(modifiers) + "\t");
            //2.数据类型
            Class<?> type = field.getType();
            System.out.print(type.getName() + "\t");
            //3.变量名
            String name = field.getName();
            System.out.print(name);
            System.out.println();
        }
    }


}
