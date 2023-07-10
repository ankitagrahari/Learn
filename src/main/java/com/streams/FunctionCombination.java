package com.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FunctionCombination {
	
	public static void functionCombination_2(){
		List<IntUnaryOperator> operatorList = List.of(
				i -> i + 1,
				i -> i * 2,
				i -> i + 3
		);
		
		IntUnaryOperator combineOpr = operatorList.stream()
				                              .reduce(IntUnaryOperator.identity(), IntUnaryOperator::andThen);
		System.out.println(combineOpr.applyAsInt(5));
	}
	
	public static void function_combination(){
		Path path = Paths.get("src/main/java/com/streams/sonnet.txt");
		try(Stream<String> lines= Files.lines(path)){
			Map<String, Long> splitSonnet = lines
					.flatMap(line -> SplitStringByChar.splitStringByChar(line).stream())
					.collect(Collectors.groupingBy(
									Function.identity(),
									Collectors.counting()
					));
			System.out.println(splitSonnet);
			System.out.println(splitSonnet.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		FunctionCombination.function_combination();
		FunctionCombination.functionCombination_2();
	}
}
