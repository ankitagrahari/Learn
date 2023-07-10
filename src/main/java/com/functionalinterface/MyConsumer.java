package com.functionalinterface;

public interface MyConsumer<T> {
	
	void accept(T t);
	
	default MyConsumer<T> process(MyConsumer<T> other){
		return (T t) -> {accept(t);other.accept(t);};
	}
}
