package com.functionalinterface;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@FunctionalInterface
public interface CurrencyConverter {
	
	double convert(double amount);
	
	interface BiFunction {
		Double convert(Double amount, String toCurr);
		
		default CurrencyConverter to(String toCurr){
			return amount -> convert(amount, toCurr);
		}
	}
	
	interface TriFunction {
		Double convert(Double amount, String fromCurr,  String toCurr);
		
		default BiFunction from(String fromCurr){
			return (amount, toCurr) -> convert(amount, fromCurr, toCurr);
		}
	}
	
	static TriFunction of(LocalDate date){
		return (amount, fromCurr, toCurr) -> {
			Path path = Paths.get("src/main/java/com/functionalinterface/data/currency.txt");
			try(Stream<String> lines = Files.lines(path)){
				Map<String, Double> currencyMap = lines.skip(1L)
						.collect(Collectors.toMap(
								l -> l.substring(0, 3),
								l -> Double.parseDouble(l.substring(4))
						));
				return amount * (currencyMap.get(toCurr) / currencyMap.get(fromCurr));
			} catch (NoSuchFileException nsfe){
				nsfe.printStackTrace();
				System.err.println("Source file is not present");
				return null;
			} catch (IOException ir) {
				ir.printStackTrace();
				return null;
			}
		};
	}
}
