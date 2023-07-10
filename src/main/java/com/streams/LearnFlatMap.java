package com.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LearnFlatMap {
	
	public static List<String> flattenStringToEachChar(List<String> strs){
//		return strs.stream()
//				.map(s -> SplitStringByChar.splitStringByChar(s))
//				.flatMap(Collection::stream)
//				.collect(Collectors.toList());
		
		return strs.stream()
				.flatMap(s -> SplitStringByChar.splitStringByChar(s).stream())
				.collect(Collectors.toList());
	}
	
	public static void splitSonnet(){
		Path path = Paths.get("src/main/java/com/streams/sonnet.txt");
		try(Stream<String> lines= Files.lines(path)){
			List<String> splitSonnet = lines.flatMap(line -> Arrays.stream(line.split(" +")))
					.collect(Collectors.toList());
			System.out.println(splitSonnet);
			System.out.println(splitSonnet.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		List<String> list = new ArrayList<>(List.of("alfa", "bravo", "charlie"));
		System.out.println(LearnFlatMap.flattenStringToEachChar(list));
		LearnFlatMap.splitSonnet();
	}
}
