package com.cyh.java8;

@FunctionalInterface
public interface MyPredicate<T> {

    boolean test(T t);

}
