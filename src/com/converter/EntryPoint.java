package com.converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class EntryPoint {

	private static final String USD = "USD";
	private static final String CAD = "CAD";
	private static final String PESO = "PESO";
	private static final String EURO = "EURO";
	private static final String YUAN = "YUAN";
	private static final String YEN = "YEN";
	private static final String EGP = "EGP";
	private static final String[] currencies = { CAD, PESO, EURO, YUAN, YEN, EGP };

	public static void main(String[] args) throws IOException {
		CurrencyManager converter = new EntryPoint().new CurrencyManager();
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
			if (!verifyInput(targetCurrency)) {
				System.err.println("Invalid currency, please type the currency name");
				main(args);
				return;
			}
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
			if (!verifyInput(sourceCurrency)) {
				System.err.println("Invalid currency, please type the currency name");
				main(args);
				return;
			}
			break;
		}

		System.out.println("Insert the amount to convert (" + converter.guessCurrency(sourceCurrency) + " => " + converter.guessCurrency(targetCurrency) + ") : ");
		Double amount = Double.valueOf(reader.readLine());

		result = converter.convert(sourceCurrency, targetCurrency, amount);
		System.out.println("Convertion success : " + amount + " " + converter.guessCurrency(sourceCurrency) + " = " + new DecimalFormat("#.##").format(result) + " " + converter.guessCurrency(targetCurrency));
	}

	private static boolean verifyInput(String choosenCurrency) {
		boolean match = false;
		for (String currency : currencies) {
			if (currency.equals(choosenCurrency)) {
				match = true;
			}
		}
		return match;
	}

	class CurrencyManager {

		public Double convert(String sourceCurrency, String targetCurrency, Double amount) {
			Double scale = getScale(sourceCurrency, targetCurrency);
			return amount * scale;
		}

		private Double getScale(String sourceCurrency, String targetCurrency) {
			Double scale = null;
			String choice = targetCurrency.equals(USD) ? sourceCurrency : targetCurrency;
			switch (choice) {
			case CAD:
				scale = 1.33;
				break;
			case PESO:
				scale = 19.55;
				break;
			case EURO:
				scale = 0.91;
				break;
			case YUAN:
				scale = 7.03;
				break;
			case YEN:
				scale = 109.84;
				break;
			case EGP:
				scale = 16.12;
				break;
			}

			if (targetCurrency.equals(USD)) {
				scale = 1 / scale;
			}

			return scale;
		}

		public String guessCurrency(String choice) {
			switch (choice) {
			case CAD:
				return "CAD";
			case PESO:
				return "PESO";
			case EURO:
				return "EURO";
			case YUAN:
				return "YUAN (CHINA)";
			case YEN:
				return "YEN (JAPAN)";
			case EGP:
				return "EGP (EGYPT)";
			default:
				return "USD";
			}
		}

	}

}
