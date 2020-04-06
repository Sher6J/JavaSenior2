package cn.sher6j.java2;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 一、构造器引用
 *      和方法引用类似，
 *      函数式接口的抽象方法的形参列表和构造器的形参列表一致，
 *      抽象方法的返回值类型即为构造器所属的类的类型
 * 二、数组引用
 *      可以把数组看做是一个特殊的类，则写法与构造器引用相同
 */
public class ConstructorRefTest {
	//构造器引用
    //Supplier中的T get()
    //Employee的空参构造器Employee()
    @Test
    public void test1(){
        //原始
        Supplier<Employee> s1 = new Supplier<Employee>() {
            @Override
            public Employee get() {
                return new Employee();
            }
        };
        System.out.println(s1.get());

        System.out.println("----------------------");

        //Lambda表达式
        Supplier<Employee> s2 = () -> new Employee();
        System.out.println(s2.get());

        System.out.println("----------------------");

        //构造器引用
        Supplier<Employee> s3 = Employee::new;
        System.out.println(s3.get());

        System.out.println("----------------------");
	}

	//Function中的R apply(T t)
    @Test
    public void test2(){
        Function<Integer, Employee> f1 = id -> new Employee(id);
        System.out.println(f1.apply(102));

        System.out.println("----------------");

        Function<Integer, Employee> f2 = Employee::new;
        System.out.println(f2.apply(22));
    }

	//BiFunction中的R apply(T t,U u)
    @Test
    public void test3(){
        BiFunction<Integer, String, Employee> f1 = (id, name) -> new Employee(id, name);
        System.out.println(f1.apply(11, "james"));

        System.out.println("------------------");
        BiFunction<Integer, String, Employee> f2 = Employee::new;
        System.out.println(f2.apply(23, "lebron"));
    }

	//数组引用
    //Function中的R apply(T t)
    @Test
    public void test4(){
        Function<Integer, String[]> f1 = length -> new String[length];
        String[] a1 = f1.apply(5);
        System.out.println(Arrays.toString(a1));

        System.out.println("--------------------");

        Function<Integer, String[]> f2 = String[]::new;
        String[] a2 = f2.apply(10);
        System.out.println(Arrays.toString(a2));

    }
}
