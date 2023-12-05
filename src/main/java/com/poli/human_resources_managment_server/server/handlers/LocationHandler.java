package com.poli.human_resources_managment_server.server.handlers;

import com.poli.human_resources_managment_server.db.LocationDAO;
import com.poli.human_resources_managment_server.model.Location;
import java.util.StringTokenizer;

/**
 * The LocationHandler class is responsible for processing requests related to
 * location records. It interacts with the LocationDAO to perform various
 * operations on location data in the database.
 */
public class LocationHandler {

	private final LocationDAO locationDAO;

	/**
	 * Constructs a new LocationHandler instance and initializes the associated
	 * LocationDAO.
	 */
	public LocationHandler() {
		this.locationDAO = new LocationDAO();
	}

	/**
	 * Handles incoming requests related to location records.
	 *
	 * @param request The request string containing a command and optional
	 *                parameters.
	 * @return A response string based on the executed command.
	 */
	public String handleRequest(String request) {
		StringTokenizer st = new StringTokenizer(request);
		String command = st.nextToken();

		switch (command) {
		case "LOCATION_GET_BY_ID":
			return getLocationById(st);
		case "LOCATION_INSERT":
			return insertLocation(st);
		case "LOCATION_UPDATE":
			return updateLocation(st);
		default:
			return "Invalid location command";
		}
	}

	/**
	 * Retrieves a location record by its unique identifier.
	 *
	 * @param st A StringTokenizer containing the command and parameters.
	 * @return A string representation of the location record if found, or an error
	 *         message if not found.
	 */
	private String getLocationById(StringTokenizer st) {
		int id = Integer.parseInt(st.nextToken());
		Location location = locationDAO.getLocationById(id);
		return location != null ? location.toString() : "Location not found";
	}

	/**
	 * Inserts a new location record into the database.
	 *
	 * @param st A StringTokenizer containing the command and parameters.
	 * @return A success message if the insertion is successful, or an error message
	 *         if it fails.
	 */
	private String insertLocation(StringTokenizer st) {
		st.nextToken("\"");
		String address = st.nextToken("\"");
		st.nextToken(" ");
		int cityId = Integer.parseInt(st.nextToken());
		boolean success = locationDAO.insertLocation(new Location(address, cityId));
		return success ? "Location inserted successfully" : "Failed to insert location";
	}

	/**
	 * Updates an existing location record in the database.
	 *
	 * @param st A StringTokenizer containing the command and parameters.
	 * @return A success message if the update is successful, or an error message if
	 *         it fails.
	 */
	private String updateLocation(StringTokenizer st) {
		int id = Integer.parseInt(st.nextToken());
		st.nextToken("\"");
		String newAddress = st.nextToken("\"");
		st.nextToken(" ");
		int newCityId = Integer.parseInt(st.nextToken());
		boolean success = locationDAO.updateLocation(new Location(id, newAddress, newCityId));
		return success ? "Location updated successfully" : "Failed to update location";
	}
}
