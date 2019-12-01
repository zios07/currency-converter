package com.converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EntryPoint {

	public static void main(String[] args) throws IOException {
		Converter converter = new Converter();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please select the type of the currency you want to convert from : ");
		System.out.println("1 - CAD");
		System.out.println("2 - PESO (MEXICO)");
		System.out.println("2 - EURO");
		System.out.println("2 - YUAN (CHINA)");
		System.out.println("2 - YEN (JAPAN)");
		System.out.println("2 - EGP (EGYPT)");
		String sourceCurrency = reader.readLine();
		System.out.println("Insert the amount to convert : ");
		String amount = reader.readLine();
		System.out.println("Please select the type of the currency you want to convert to : ");
		String targetCurrency = reader.readLine();
		Double result = converter.convert(sourceCurrency, targetCurrency, amount);
		System.out.println("The result is : " + result);
	}
}

class Converter {

	public Double convert(String sourceCurrency, String targetCurrency, String amount) {
		return null;
	}

}