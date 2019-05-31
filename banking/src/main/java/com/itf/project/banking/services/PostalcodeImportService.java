package com.itf.project.banking.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.itf.project.banking.models.Postalcode;

public class PostalcodeImportService {

	public static ArrayList<String> importPostalcodesAsList() throws FileNotFoundException, IOException {

		ArrayList<List<String>> records = new ArrayList<>();
		ArrayList<String> postalcodes = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/plz_de.csv"))) {

			String line;

			while ((line = br.readLine()) != null) {

				String[] values = line.split(",");

				records.add(Arrays.asList(values));
			}

			for (List<String> values : records) {

				postalcodes.add(values.get(1));
				postalcodes.add(values.get(2));

			}

			return postalcodes;
		}

	}

	public static List<Postalcode> postalcodeFactory(ArrayList<String> postalcodesAsString) {

		List<Postalcode> postalcodes = new ArrayList<>();

		

		for (int i = 0; i <= postalcodesAsString.size()/10; i = i + 2) {


			for (int j = 1; j <= postalcodesAsString.size()/10; j = j + 2) {

				postalcodes.add(new Postalcode(postalcodesAsString.get(i), postalcodesAsString.get(j), "Deutschland"));

			}

		}

		return postalcodes;

	}

}
