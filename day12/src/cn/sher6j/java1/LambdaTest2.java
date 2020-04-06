package cn.sher6j.java1;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Java内置的4大核心函数式接口
 *
 * 消费型接口    Consumer<T>     void accept(T t)    给东西不往回返
 * 供给型接口    Supplier<T>     T get()             不给东西往回返
 * 函数型接口    Function<T, R>  R apply(T t)        给T返回R
 * 断定型接口    Predicate<T>    boolean test(T t)   判定true或false
 * @author sher6j
 * @create 2020-04-06-8:14
 */
public class LambdaTest2 {

    @Test
    public void test1() {
        //本来
        happyTime(500, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("钱数是" + aDouble);
            }
        });

        System.out.println("-------------------");

        //Lambda表达式
        happyTime(400, money -> System.out.println("钱数是" + money));
    }

    public void happyTime(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }


    @Test
    public void test2() {
        //原来
        List<String> list = Arrays.asList("北京","杭州","上海","东京","南京");
        List<String> filterString1 = filterString(list, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("京");
            }
        });
        System.out.println(filterString1);

        System.out.println("----------------------");

        //Lambda表达式
        List<String> filterString2 = filterString(list, s -> s.contains("京"));
        System.out.println(filterString2);
    }

    //根据给定规则过滤集合中的字符串，规则由Predicate中的方法决定
    public List<String> filterString(List<String> list, Predicate<String> predicate) {
        ArrayList<String> filterList = new ArrayList<>();

        for (String s : list) {
            if (predicate.test(s)) {
                filterList.add(s);
            }
        }

        return filterList;
    }
}
