package com.cyh.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Java8 内置的四大核心函数式接口
 *
 * Consumer<T> : 消费型接口
 * 		void accept(T t);
 *
 * Supplier<T> : 供给型接口
 * 		T get();
 *
 * Function<T, R> : 函数型接口
 * 		R apply(T t);
 *
 * Predicate<T> : 断言型接口
 * 		boolean test(T t);
 *
 */
public class TestLambda3 {

    //Consumer<T> 消费型接口
    @Test
    public void test1() {
        happy(10000d, m -> System.out.println("消费：" + m));
    }

    private void happy(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

    //Supplier<T> 供给型接口 :
    @Test
    public void test2() {
        getNumList(10, () -> (int) (Math.random() * 100)).forEach(System.out::println);
    }

    private List<Integer> getNumList(int num, Supplier<Integer> supplier) {
        List<Integer> list = new ArrayList<>();
        while (num-- > 0) {
            list.add(supplier.get());
        }
        return list;
    }

    //Function<T, R> 函数型接口：
    @Test
    public void test3() {
        System.out.println(strHandler("\t\t Hello World  ", s -> s.trim()));
        System.out.println(strHandler("Hello World", s -> s.substring(6, 11)));
    }

    private String strHandler(String str, Function<String, String> function) {
        return function.apply(str);
    }

    //Predicate<T> 断言型接口：
    @Test
    public void test4() {
        List<String> list = Arrays.asList("Hello", "a", "World", "c");
        filterString(list, s -> s.length() > 3).forEach(System.out::println);
    }

    private List<String> filterString(List<String> list, Predicate<String> predicate) {
        List<String> result = new ArrayList<>();
        for (String s : list) {
            if (predicate.test(s)) {
                result.add(s);
            }
        }
        return result;
    }

}
