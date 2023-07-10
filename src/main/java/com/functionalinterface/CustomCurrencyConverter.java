package com.functionalinterface;


import java.time.LocalDate;

// A class which converts the currency based on date.
public class CustomCurrencyConverter {
	
	public static void main(String[] args) {
		LocalDate date = LocalDate.now();
		CurrencyConverter currConverter = CurrencyConverter.of(date)
				.from(String.valueOf(Currency.USD))
				.to(String.valueOf(Currency.INR));
		
		System.out.println("From USD to INR:"+currConverter.convert(30));
	}
}

enum Currency{
	USD, INR, EUR, GBP;
}
