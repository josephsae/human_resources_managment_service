package com.poli.human_resources_managment_server.server.handlers;

import com.poli.human_resources_managment_server.db.CityDAO;
import com.poli.human_resources_managment_server.model.City;
import java.util.StringTokenizer;

/**
 * The CityHandler class is responsible for processing requests related to city
 * records. It interacts with the CityDAO to perform various operations on city
 * data in the database.
 */
public class CityHandler {

	private final CityDAO cityDAO;

	/**
	 * Constructs a new CityHandler instance and initializes the associated CityDAO.
	 */
	public CityHandler() {
		this.cityDAO = new CityDAO();
	}

	/**
	 * Handles incoming requests related to city records.
	 *
	 * @param request The request string containing a command and optional
	 *                parameters.
	 * @return A response string based on the executed command.
	 */
	public String handleRequest(String request) {
		StringTokenizer st = new StringTokenizer(request);
		String command = st.nextToken();

		switch (command) {
		case "CITY_GET_BY_ID":
			return getCityById(st);
		case "CITY_INSERT":
			return insertCity(st);
		case "CITY_UPDATE":
			return updateCity(st);
		default:
			return "Invalid city command";
		}
	}

	/**
	 * Retrieves a city record by its unique identifier.
	 *
	 * @param st A StringTokenizer containing the command and parameters.
	 * @return A string representation of the city record if found, or an error
	 *         message if not found.
	 */
	private String getCityById(StringTokenizer st) {
		int id = Integer.parseInt(st.nextToken());
		City city = cityDAO.getCityById(id);
		return city != null ? city.toString() : "City not found";
	}

	/**
	 * Inserts a new city record into the database.
	 *
	 * @param st A StringTokenizer containing the command and parameters.
	 * @return A success message if the insertion is successful, or an error message
	 *         if it fails.
	 */
	private String insertCity(StringTokenizer st) {
		String name = st.nextToken();
		int countryId = Integer.parseInt(st.nextToken());
		boolean success = cityDAO.insertCity(new City(name, countryId));
		return success ? "City inserted successfully" : "Failed to insert city";
	}

	/**
	 * Updates an existing city record in the database.
	 *
	 * @param st A StringTokenizer containing the command and parameters.
	 * @return A success message if the update is successful, or an error message if
	 *         it fails.
	 */
	private String updateCity(StringTokenizer st) {
		int id = Integer.parseInt(st.nextToken());
		String newName = st.nextToken();
		int newCountryId = Integer.parseInt(st.nextToken());
		boolean success = cityDAO.updateCity(new City(id, newName, newCountryId));
		return success ? "City updated successfully" : "Failed to update city";
	}

}
