package cn.sher6j.java3;

import cn.sher6j.java2.Employee;
import cn.sher6j.java2.EmployeeData;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * 1. Stream关注的是对数据的运算，与CPU打交道
 *    集合关注的是数据的存储，与内存打交道
 * 2.
 *    ①Stream 自己不会存储元素。
 *    ②Stream 不会改变源对象。相反，他们会返回一个持有结果的新Stream。
 *    ③Stream 操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。
 * 3. Stream执行流程
 *    ①Stream的实例化
 *    ②一系列的中间操作（过滤、映射、……）
 *    ③终止操作
 *
 * 测试Stream的实例化
 *
 * @author sher6j
 * @create 2020-04-06-16:11
 */
public class StreamAPITest {
    //创建Stream方式一：通过集合
    @Test
    public void test1() {
        List<Employee> employees = EmployeeData.getEmployees();

//        default Stream<E> stream() : 返回一个顺序流
        Stream<Employee> stream = employees.stream();

//        default Stream<E> parallelStream() : 返回一个并行流
        Stream<Employee> parallelStream = employees.parallelStream();
    }

    //创建Stream方式二：通过数组
    @Test
    public void test2() {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        //调用Arrays的静态方法static <T> Stream<T> stream(T[] array): 返回一个流
        IntStream stream = Arrays.stream(arr);
    }

    //创建Stream方式三：通过Stream 的of()
    @Test
    public void test3() {
        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
    }

    //创建Stream方式四：创建无限流
    @Test
    public void test4() {

//        迭代
//        public static<T> Stream<T> iterate(final T seed, final UnaryOperator<T> f)
        //遍历前10个偶数
        Stream.iterate(0, t -> t + 2).limit(10).forEach(System.out::println);

//        生成
//        public static<T> Stream<T> generate(Supplier<T> s)
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }
}
