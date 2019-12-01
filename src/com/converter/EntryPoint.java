package com.converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EntryPoint {

	private static final String USD = "0";

	public static void main(String[] args) throws IOException {
		Converter converter = new EntryPoint().new Converter();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		Double result = 0d;
		String targetCurrency = null;
		String sourceCurrency = null;

		System.out.println("Please select the mode you want to use : ");
		System.out.println("1 - USD to other currencies");
		System.out.println("2 - Other currencies to USD");
		String modeString = reader.readLine();

		int mode = 0;
		try {
			mode = Integer.parseInt(modeString);
		} catch (Exception e) {
			System.out.println("Invalid choice");
		}

		switch (mode) {
		// Convertion from USD
		case 1:
			System.out.println("Please select the type of the currency you want to convert to : ");
			System.out.println("1 - CAD");
			System.out.println("2 - PESO (MEXICO)");
			System.out.println("3 - EURO");
			System.out.println("4 - YUAN (CHINA)");
			System.out.println("5 - YEN (JAPAN)");
			System.out.println("6 - EGP (EGYPT)");
			targetCurrency = reader.readLine();
			sourceCurrency = USD;
			break;
		// Conversion to USD
		case 2:
			System.out.println("Please select the type of the currency you want to convert from : ");
			System.out.println("1 - CAD");
			System.out.println("2 - PESO");
			System.out.println("3 - EURO");
			System.out.println("4 - YUAN (CHINA)");
			System.out.println("5 - YEN (JAPAN)");
			System.out.println("6 - EGP (EGYPT)");
			targetCurrency = USD;
			sourceCurrency = reader.readLine();
			break;
		}

		System.out.println("Insert the amount to convert : ");
		Double amount = Double.valueOf(reader.readLine());

		result = converter.convert(sourceCurrency, targetCurrency, amount);
		System.out.println("The result is : " + result);
	}

	class Converter {

		public Double convert(String sourceCurrency, String targetCurrency, Double amount) {
			Double scale = getScale(sourceCurrency, targetCurrency);
			return amount * scale;
		}

		private Double getScale(String sourceCurrency, String targetCurrency) {
			Double scale = null;
			String choice = targetCurrency.equals("0") ? sourceCurrency : targetCurrency;
			switch (choice) {
			case "1":
				scale = 1.33;
				break;
			case "2":
				scale = 19.55;
				break;
			case "3":
				scale = 0.91;
				break;
			case "4":
				scale = 7.03;
				break;
			case "5":
				scale = 109.84;
				break;
			case "6":
				scale = 16.12;
				break;
			}

			if (targetCurrency.equals("0")) {
				scale = 1 / scale;
			}

			return scale;
		}
	}

}
