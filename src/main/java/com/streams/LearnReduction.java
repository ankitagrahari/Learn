package com.streams;

import java.math.BigInteger;
import java.util.stream.LongStream;

public class LearnReduction {
	
	public static void main(String[] args) {
		//find Factorials for 21
		
		BigInteger result = LongStream.rangeClosed(1, 21)
				.mapToObj(BigInteger::valueOf)
				.reduce(BigInteger.ONE, BigInteger::multiply);
		
		System.out.println(result);
	}
}
