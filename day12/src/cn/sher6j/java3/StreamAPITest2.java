package cn.sher6j.java3;

import cn.sher6j.java2.Employee;
import cn.sher6j.java2.EmployeeData;
import org.junit.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 测试Stream的终止操作
 *
 * @author sher6j
 * @create 2020-04-06-17:05
 */
public class StreamAPITest2 {
    //1-匹配与查找
    @Test
    public void test1() {
        List<Employee> employees = EmployeeData.getEmployees();

//        allMatch(Predicate p) 检查是否匹配所有元素
        //练习：是否所有员工年龄都大于18岁
        boolean allMatch = employees.stream().allMatch(e -> e.getAge() > 18);
        System.out.println(allMatch);

        System.out.println("----------");

//        anyMatch(Predicate p) 检查是否至少匹配一个元素
        //练习：是否存在员工的工资大于10000
        boolean anyMatch = employees.stream().anyMatch(e -> e.getSalary() > 10000);
        System.out.println(anyMatch);

        System.out.println("----------");

//        noneMatch(Predicate p) 检查是否没有匹配所有元素
        //练习：是否存在员工姓“雷”
        boolean noneMatch = employees.stream().noneMatch(e -> e.getName().startsWith("雷"));
        System.out.println(noneMatch);

        System.out.println("-----------");

//        findFirst() 返回第一个元素  -----  可以和排序一起使用
        Optional<Employee> employee = employees.stream().findFirst();
        System.out.println(employee);

        System.out.println("------------");

//        findAny() 返回当前流中的任意元素
        Optional<Employee> employee1 = employee.stream().findAny();
        System.out.println(employee1);
    }

    //1-匹配与查找
    @Test
    public void test2() {
        List<Employee> employees = EmployeeData.getEmployees();

//        count() 返回流中元素总数
        System.out.println(employees.stream().filter(e -> e.getSalary() > 5000).count());

        System.out.println("------------");

//        max(Comparator c) 返回流中最大值
        //练习：返回最高的工资
        Stream<Double> salaryStream = employees.stream().map(e -> e.getSalary());
        System.out.println(salaryStream.max(Double::compare));

        System.out.println("------------");

//        min(Comparator c) 返回流中最小值
        //练习：返回最低工资的员工
        Optional<Employee> employee = employees.stream().min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(employee);

        System.out.println("-------------");

//        forEach(Consumer c) 内部迭代(使用 Collection 接口需要用户去做迭代，称为外部迭代。相反，Stream API 使用内部迭 代——它帮你把迭代做了)
        employees.stream().forEach(System.out::println);
        System.out.println();
        //使用集合的遍历操作
        employees.forEach(System.out::println);
    }

    //2-归约
    @Test
    public void test3() {

//        reduce(T iden, BinaryOperator b) 可以将流中元素反复结合起来，得到一个值。返回 T
        //练习：计算1-10的自然数的和
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        /*
        public interface BinaryOperator<T> extends BiFunction<T,T,T>
        T,T,T ------ a,b,a+b  可以用方法引用
        public static int sum(int a, int b) {return a + b;}
         */
        System.out.println(list.stream().reduce(0, Integer::sum));//identity是初始值

//        reduce(BinaryOperator b) 可以将流中元素反复结合起来，得到一 个值。返回 Optional<T>
        //练习：计算公司所有员工工资的总和
        List<Employee> employees = EmployeeData.getEmployees();
        Stream<Double> salaryStream = employees.stream().map(e -> e.getSalary());
//        Optional<Double> reduce = salaryStream.reduce(Double::sum);
        Optional<Double> reduce = salaryStream.reduce((d1, d2) -> d1 + d2);
        System.out.println(reduce);
    }

    //3-收集
    @Test
    public void test4() {
//        collect(Collector c)
//        将流转换为其他形式。接收一个 Collector 接口的实现，用于给Stream中元素做汇总的方法
        //练习：查找工资大于6000的员工，结果返回一个List或Set
        List<Employee> employees = EmployeeData.getEmployees();

        List<Employee> employeeList = employees.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toList());
        employeeList.forEach(System.out::println);

        System.out.println("---------------");

        Set<Employee> employeeSet = employees.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toSet());
        employeeSet.forEach(System.out::println);
    }
}
