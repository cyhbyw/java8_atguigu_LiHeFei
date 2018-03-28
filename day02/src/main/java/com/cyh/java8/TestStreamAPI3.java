package com.cyh.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import com.cyh.java8.Employee.Status;

public class TestStreamAPI3 {

    List<Employee> emps = Arrays.asList(new Employee(102, "李四", 79, 6666.66, Status.BUSY),
        new Employee(101, "张三", 18, 9999.99, Status.FREE),
        new Employee(103, "王五", 28, 3333.33, Status.VOCATION),
        new Employee(104, "赵六", 8, 7777.77, Status.BUSY),
        new Employee(104, "赵六", 8, 7777.77, Status.FREE),
        new Employee(104, "赵六", 8, 7777.77, Status.FREE),
        new Employee(105, "田七", 38, 5555.55, Status.BUSY));

    /**
    	归约
    	reduce(T identity, BinaryOperator) / reduce(BinaryOperator) ——可以将流中元素反复结合起来，得到一个值。
     */

    @Test
    public void test1() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer sum = list.stream().reduce(0, (x, y) -> x + y);
        System.out.println(sum);

        Optional<Double> optional = emps.stream().map(Employee::getSalary).reduce(Double::sum);
        System.out.println(optional.get());
    }

    //需求：搜索名字中 “六” 出现的次数
    @Test
    public void test2() {
        Optional<Integer> reduce = emps.stream().map(Employee::getName)
            .flatMap(TestStreamAPI1::filterCharacter).map(ch -> ch.equals('六') ? 1 : 0)
            .reduce(Integer::sum);
        System.out.println(reduce.get());
    }

    //collect——将流转换为其他形式。接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
    @Test
    public void test3() {
        List<String> list = emps.stream().map(Employee::getName).collect(Collectors.toList());
        list.forEach(System.out::println);

        System.out.println("----------------------------------");

        Set<String> set = emps.stream().map(Employee::getName).collect(Collectors.toSet());
        set.forEach(System.out::println);

        System.out.println("----------------------------------");

        HashSet<String> hashSet = emps.stream().map(Employee::getName)
            .collect(Collectors.toCollection(HashSet::new));
        hashSet.forEach(System.out::println);
    }

    @Test
    public void test4() {
        Optional<Double> max = emps.stream().map(Employee::getSalary)
            .collect(Collectors.maxBy(Double::compare));
        System.out.println(max.get());

        Optional<Employee> minExp = emps.stream()
            .collect(Collectors.minBy(Comparator.comparingDouble(Employee::getSalary)));
        System.out.println(minExp.get());

        Double sum = emps.stream().collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum);

        Double avgSalary = emps.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(avgSalary);

        Long count = emps.stream().collect(Collectors.counting());
        System.out.println(count);

        DoubleSummaryStatistics summaryStatistics = emps.stream()
            .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(summaryStatistics.getMax() + "  " + summaryStatistics.getAverage());
    }

    //分组
    @Test
    public void test5() {
        Map<Status, List<Employee>> map = emps.stream()
            .collect(Collectors.groupingBy(Employee::getStatus));
        System.out.println(map);
    }

    //多级分组
    @Test
    public void test6() {
        Map<Status, Map<String, List<Employee>>> map = emps.stream()
            .collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e -> {
                if (e.getAge() >= 60) {
                    return "老年";
                } else if (e.getAge() >= 35) {
                    return "中年";
                } else {
                    return "成年";
                }
            })));
        System.out.println(map);
    }

    //分区
    @Test
    public void test7() {
        Map<Boolean, List<Employee>> map = emps.stream()
            .collect(Collectors.partitioningBy(e -> e.getSalary() >= 5000));
        System.out.println(map);
    }

    @Test
    public void test8() {
        String s = emps.stream().map(Employee::getName).collect(Collectors.joining(", "));
        System.out.println(s);
    }

    @Test
    public void test9() {
        Optional<Double> sum = emps.stream().map(Employee::getSalary)
            .collect(Collectors.reducing(Double::sum));
        System.out.println(sum.get());
    }

}
