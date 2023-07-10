package com.functionalinterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class LearnComparator {
	
	public void comparing(){
		Person michael = new Person("Jackson", "Michael" , 51);
	    Person rod = new Person("Rod", "Stewart", 71);
	    Person paul = new Person("Paul", "McCartney", 74);
	    Person mick = new Person("Mick", "Jagger", 73);
	    Person jermaine = new Person("Jackson","Jermaine", 61);
		
		Comparator<Person> comp = Comparator.comparing(Person::lastName);
		System.out.println(comp.compare(michael, rod));
		
		Comparator<Person> nullsLastComp = Comparator.nullsLast(Comparator.comparing(Person::lastName));
		System.out.println("Nulls at last:"+nullsLastComp.compare(paul, null));
		
		Function<Person, String> func = p -> p.lastName();
		Function<Person, String> func1 = p -> p.firstName();
		Function<Person, Integer> func2 = p -> p.age();
		
		MyComparator<Person> myComparator = MyComparator.comparing(func)
				                                    .thenComparing(func1)
				                                    .thenComparing(func2);
		
		System.out.println("Compare based on LN, FN, & Age:"+myComparator.compare(michael, jermaine));
		
		Person nullP = null; Person nullP1 = null;
		MyComparator<Person> myComparator1 = MyComparator.nullsLast(myComparator);
		System.out.println("Compare with Nulls as greater than Non-Null:"+myComparator1.compare(michael, nullP));
		
		MyComparator<Person> reversMyComp = myComparator.reversed();
		System.out.println("Reverse:"+reversMyComp.compare(michael, jermaine));
	}
	
	public static void main(String[] args) {
		LearnComparator obj = new LearnComparator();
		obj.comparing();
		
		int min = Integer.MIN_VALUE;
		int negMin = -Integer.MIN_VALUE;
		System.out.println(min+"---"+negMin);
	}
}

record Person(String lastName, String firstName, int age){}