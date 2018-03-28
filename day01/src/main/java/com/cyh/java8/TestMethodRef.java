package com.cyh.java8;

import org.junit.Test;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 一、方法引用：若 Lambda 体中的功能，已经有方法提供了实现，可以使用方法引用
 * 			  （可以将方法引用理解为 Lambda 表达式的另外一种表现形式）
 *
 * 1. 对象的引用 :: 实例方法名
 *
 * 2. 类名 :: 静态方法名
 *
 * 3. 类名 :: 实例方法名
 *
 * 注意：
 * 	 ①方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致！
 * 	 ②若Lambda 的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： ClassName::MethodName
 *
 * 二、构造器引用 :构造器的参数列表，需要与函数式接口中参数列表保持一致！
 *
 * 1. 类名 :: new
 *
 * 三、数组引用
 *
 * 	类型[] :: new;
 *
 *
 */
public class TestMethodRef {
    @Test
    public void test1() {
        PrintStream printStream = System.out;
        Consumer<String> consumer = s -> printStream.println(s);
        consumer.accept("Hello World");

        Consumer<String> consumer1 = printStream::println;
        consumer1.accept("Hello Java 8");

        Consumer<String> consumer2 = System.out::println;
        consumer2.accept("Hello Consumer");
    }

    //对象的引用 :: 实例方法名
    @Test
    public void test2() {
        Employee employee = new Employee(101, "张三", 18, 9999d);
        Supplier<String> supplier = () -> employee.getName();
        System.out.println(supplier.get());

        Supplier<String> supplier1 = employee::getName;
        System.out.println(supplier1.get());
    }

    @Test
    public void test3() {
        BiFunction<Double, Double, Double> function = (x, y) -> Math.max(x, y);
        System.out.println(function.apply(1.5, 2.2));

        BiFunction<Double, Double, Double> function1 = Math::max;
        System.out.println(function1.apply(3.5, 2.2));
    }

    //类名 :: 静态方法名
    @Test
    public void test4() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
        comparator = Integer::compare;
    }

    //类名 :: 实例方法名
    @Test
    public void test5() {
        BiPredicate<String, String> predicate = (x, y) -> x.equals(y);
        System.out.println(predicate.test("a", "a"));

        predicate = String::equals;
        System.out.println(predicate.test("b", "b"));

        Function<Employee, String> function = e -> e.show();
        System.out.println(function.apply(new Employee()));

        function = Employee::show;
        System.out.println(function.apply(new Employee()));
    }

    @Test
    public void test6() {
        Supplier<Employee> supplier = () -> new Employee();
        System.out.println(supplier.get());

        supplier = Employee::new;
        System.out.println(supplier.get());
    }

    //构造器引用
    @Test
    public void test7() {
        Function<String, Employee> function = Employee::new;
        BiFunction<String, Integer, Employee> biFunction = Employee::new;
    }

    //数组引用
    @Test
    public void test8() {
        Function<Integer, String[]> function = args -> new String[args];
        String[] strings = function.apply(5);
        System.out.println(strings.length);

        Function<Integer, Employee[]> function1 = Employee[]::new;
        Employee[] employees = function1.apply(5);
        System.out.println(employees.length);
    }

}
