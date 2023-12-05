package com.poli.human_resources_managment_server.server.handlers;

import com.poli.human_resources_managment_server.db.CountryDAO;
import com.poli.human_resources_managment_server.model.Country;
import java.util.StringTokenizer;

/**
 * The CountryHandler class is responsible for processing requests related to
 * country records. It interacts with the CountryDAO to perform various
 * operations on country data in the database.
 */
public class CountryHandler {

	private final CountryDAO countryDAO;

	/**
	 * Constructs a new CountryHandler instance and initializes the associated
	 * CountryDAO.
	 */
	public CountryHandler() {
		this.countryDAO = new CountryDAO();
	}

	/**
	 * Handles incoming requests related to country records.
	 *
	 * @param request The request string containing a command and optional
	 *                parameters.
	 * @return A response string based on the executed command.
	 */
	public String handleRequest(String request) {
		StringTokenizer st = new StringTokenizer(request);
		String command = st.nextToken();

		switch (command) {
		case "COUNTRY_GET_BY_ID":
			return getCountryById(st);
		case "COUNTRY_INSERT":
			return insertCountry(st);
		case "COUNTRY_UPDATE":
			return updateCountry(st);
		default:
			return "Invalid country command";
		}
	}

	/**
	 * Retrieves a country record by its unique identifier.
	 *
	 * @param st A StringTokenizer containing the command and parameters.
	 * @return A string representation of the country record if found, or an error
	 *         message if not found.
	 */
	private String getCountryById(StringTokenizer st) {
		int id = Integer.parseInt(st.nextToken());
		Country country = countryDAO.getCountryById(id);
		return country != null ? country.toString() : "Country not found";
	}

	/**
	 * Inserts a new country record into the database.
	 *
	 * @param st A StringTokenizer containing the command and parameters.
	 * @return A success message if the insertion is successful, or an error message
	 *         if it fails.
	 */
	private String insertCountry(StringTokenizer st) {
		String name = st.nextToken();
		boolean success = countryDAO.insertCountry(name);
		return success ? "Country inserted successfully" : "Failed to insert country";
	}

	/**
	 * Updates an existing country record in the database.
	 *
	 * @param st A StringTokenizer containing the command and parameters.
	 * @return A success message if the update is successful, or an error message if
	 *         it fails.
	 */
	private String updateCountry(StringTokenizer st) {
		int id = Integer.parseInt(st.nextToken());
		String newName = st.nextToken();
		boolean success = countryDAO.updateCountry(id, newName);
		return success ? "Country updated successfully" : "Failed to update country";
	}
}
