package com.functionalinterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class LearnSupplier {
	
	public static void main(String[] args) {
		MySupplier<Integer> mySupplier = () -> 20;
		
		MySupplier<List<String>> supplier = () -> new ArrayList<>();
		BiConsumer<List<String>, String> accumulator = (list, a) -> list.add(a);
		BiConsumer<List<String>, List<String>> merge = (list, list2) -> list.addAll(list2);
		
		List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c","d"));
		List<String> l = list.stream()
				.collect(
						supplier,
						accumulator,
						merge
				);
		System.out.println(l);
	}
}
