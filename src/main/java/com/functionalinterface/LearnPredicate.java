package com.functionalinterface;

import java.util.function.Predicate;

public class LearnPredicate {
	
	public void predicate1(){
		Predicate<String> pred = s -> s.isEmpty();
		Predicate<String> pred1 = pred.negate();
		System.out.println(pred.test(""));
		System.out.println(pred1.test(""));
	}
	
	public void customPredicate_negate(){
		MyPredicate<String> myPred = s -> s.isEmpty();
		MyPredicate<String> notMyPred = myPred.negate();
		
		System.out.println(notMyPred.test(""));
	}
	
	public void customPredicate_xor(){
		MyPredicate<String> myPred1 = s -> s.length() == 4;
		MyPredicate<String> myPred2 = s -> s.startsWith("J");
		MyPredicate<String> myPred3 = myPred1.xor(myPred2);
		System.out.println(myPred3.test("June"));  // False
		System.out.println(myPred3.test("Julie")); // True
		System.out.println(myPred3.test("Null")); // True
	}
	
	public void customPredicate_and(){
		MyPredicate<String> myPredIsNotEmpty = s -> !s.isEmpty();
		MyPredicate<String> myPredIsNotNull = s -> !(null==s || s.isBlank());
		MyPredicate<String> myPredIsNotNullAndEmpty = myPredIsNotNull.and(myPredIsNotEmpty);
		System.out.println(myPredIsNotNullAndEmpty.test(null));
	}
	
	public static void main(String[] args) {
		LearnPredicate obj = new LearnPredicate();
//		obj.predicate_negate();
//		obj.customPredicate_and();
		obj.customPredicate_xor();
	}
}
