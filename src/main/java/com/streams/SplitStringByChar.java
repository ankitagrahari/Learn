package com.streams;

import java.util.List;
import java.util.stream.Collectors;

public class SplitStringByChar {
	
	public static List<String> splitStringByChar(String s){
		return s.codePoints()
				       .mapToObj(Character::toString)
				       .collect(Collectors.toList());
	}
	
	public static void main(String[] args) {
		SplitStringByChar obj = new SplitStringByChar();
		System.out.println(splitStringByChar("DYNAMICALLYBLUNTTECH"));
	}
}
