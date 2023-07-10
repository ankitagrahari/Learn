package com.functionalinterface;

import java.util.Objects;

@FunctionalInterface
public interface MyFunction<T, R> {
	
	R apply(T t);
	
	default <V> MyFunction<T, V> andThen(MyFunction<? super R , ? extends V> other){
		Objects.requireNonNull(other);
		return (T t) -> other.apply(apply(t));
	}
}
