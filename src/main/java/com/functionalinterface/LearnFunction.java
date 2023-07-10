package com.functionalinterface;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LearnFunction {
	
	public void function_1(){
		Function<String, String> func = s -> s.substring(0, 3);
		
		List<String> list = new ArrayList<>(List.of("aqqqqq", "bwww", "cqwe", "dwewewr"));
		list.stream()
				.filter(s -> s.length()>4)
				.map(func)
				.forEach(System.out::println);
	}
	
	public void function_2(){
		List<String> list = new ArrayList<>(List.of("aqqqqq", "bwww", "cqwe", "dwewewr"));
		MyFunction<String, String> func = new MyFunction<String, String>() {
			@Override
			public String apply(String s) {
				return s.substring(0, 4);
			}
		};
		System.out.println(func.apply("dijcnwic"));
	}
	
	public static void main(String[] args) {
		LearnFunction obj = new LearnFunction();
		obj.function_1();
		obj.function_2();
	}
}
