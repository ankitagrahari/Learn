package com.functionalinterface;

import java.util.Objects;

@FunctionalInterface
public interface MyPredicate<T> {
	
	boolean test(T t);
	
	default MyPredicate<T> negate(){
		return t -> !test(t);
	}
	
	default MyPredicate<T> and(MyPredicate<T> other){
		Objects.requireNonNull(other);
		return t -> test(t) && other.test(t);
	}
	
	default MyPredicate<T> xor(MyPredicate<T> other){
		Objects.requireNonNull(other);
		return t -> test(t) ^ other.test(t);
	}
}
