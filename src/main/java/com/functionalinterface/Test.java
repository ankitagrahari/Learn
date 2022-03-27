package com.functionalinterface;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Test {

    private void abc(){
        List<Integer> a = List.of(1, 3, 2, 1, 4, 3, 2, 5, 6, 7);

        Consumer<Integer> consumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println(integer);
            }
        };
        Consumer<Integer> consumer1 = integer -> System.out.println(integer);

        Predicate<Integer> predicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) {
                return integer%3==0;
            }
        };//x -> x % 3 == 0;
        Predicate<Integer> predicate1 = integer -> integer%3==0;

        a.stream().filter(predicate).forEach(consumer);

        Function<Integer, Integer> function = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                return 2*integer;
            }
        };
        Function<Integer, Integer> function1 = integer -> 2*integer;
        a.stream().map(function).forEach(consumer);

        Supplier<List<Integer>> supplier = new Supplier<List<Integer>>() {
            @Override
            public List<Integer> get() {
                return new ArrayList<>();
            }
        };
        Supplier<List<Integer>> supplier1 = () -> new ArrayList<>();

        List<Integer> v = supplier.get(); //acts as Factory
    }

    public static void main(String[] args) {
        Test obj = new Test();
        obj.abc();
    }
}
