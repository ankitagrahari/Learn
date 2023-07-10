package com.functionalinterface;

import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface MyComparator<T> {
	
	int compare(T t1, T t2);
	
	default <U extends Comparable<? super U>> MyComparator<T> thenComparing(Function<T, U> func){
		Objects.requireNonNull(func);
		return (t1, t2) -> {
			int cmp = this.compare(t1, t2);
			if(cmp==0){
				MyComparator<T> tComparator = comparing(func);
				return tComparator.compare(t1, t2);
			} else {
				return cmp;
			}
		};
	}
	
	default MyComparator<T> reversed(){
		return (T t1, T t2) -> this.compare(t2, t1);
	}
	
	// ? super U : means any class which is super class of U
	// ? extends U : means any class which extends U
	// A class cannot implement same interface twice with two different values of parameter type
	static <T, U extends Comparable<? super U>> MyComparator<T> comparing(Function<T, U> func){
		Objects.requireNonNull(func);
		return (t1, t2) -> {
			U u1 = func.apply(t1);
			U u2 = func.apply(t2);
			return u1.compareTo(u2);
		};
	}
	
	static <T> MyComparator<T> nullsLast(MyComparator<T> other){
		Objects.requireNonNull(other);
		return (t1, t2) -> {
			if(t1 == t2) return 0;
			else if( t1==null ) return 1;
			else if ( t2==null) return -1;
			else return other.compare(t1, t2);
		};
	}
}
