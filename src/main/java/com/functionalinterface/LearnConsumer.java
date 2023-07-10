package com.functionalinterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class LearnConsumer {
	
	public void consumer2(){
		Consumer<List<String>> consumer1 = new Consumer<List<String>>() {
			@Override
			public void accept(List<String> list) {
				list.add("first");
			}
		};
		
		Consumer<List<String>> consumer2 = new Consumer<List<String>>() {
			@Override
			public void accept(List<String> list) {
				list.add("second");
			}
		};
		
		Consumer<List<String>> consumer = new Consumer<List<String>>() {
			@Override
			public void accept(List<String> list) {
				consumer1.accept(list);
				consumer2.accept(list);
			}
		};
		
		Consumer<List<String>> consumer3 = consumer1.andThen(consumer2);
		
		List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));
		consumer.accept(list);

		List<String> list1 = new ArrayList<>(List.of("a", "b", "c"));
		consumer3.accept(list1);
		System.out.println(list);
		System.out.println(list1);
	}
	
	public void myConsumer(){
		MyConsumer<List<String>> myCons1 = (List<String> list) -> list.add("first");
		MyConsumer<List<String>> myCons2 = (List<String> list) -> list.add("second");
		
		MyConsumer<List<String>> myCons3 = myCons1.process(myCons2);
		
		List<String> list = new ArrayList<>(List.of("a", "b", "c"));
		myCons3.accept(list);
		System.out.println(list);
	}
	
	public static void main(String[] args) {
		LearnConsumer obj = new LearnConsumer();
//		obj.consumer2();
		obj.myConsumer();
	}
}
