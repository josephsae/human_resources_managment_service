package com.poli.human_resources_managment_server.model;

/**
 * Represents a physical location, typically associated with an address in a
 * city. This class encapsulates the details of a location, including its unique
 * identifier, address, and city ID.
 */
public class Location {
	private int id;
	private String address;
	private int cityId;

	/**
	 * Constructs a new Location instance without an ID, typically used for
	 * inserting new locations.
	 *
	 * @param address The address of the location.
	 * @param cityId  The unique identifier of the associated city.
	 */
	public Location(String address, int cityId) {
		this.address = address;
		this.cityId = cityId;
	}

	/**
	 * Constructs a new Location instance with an ID, typically used when retrieving
	 * data from the database.
	 *
	 * @param id      The unique identifier of the location.
	 * @param address The address of the location.
	 * @param cityId  The unique identifier of the associated city.
	 */
	public Location(int id, String address, int cityId) {
		this.id = id;
		this.address = address;
		this.cityId = cityId;
	}

	/**
	 * Retrieves the unique identifier of the location.
	 *
	 * @return The identifier of the location.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the unique identifier of the location.
	 *
	 * @param id The identifier to be set for the location.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retrieves the address of the location.
	 *
	 * @return The address of the location.
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address of the location.
	 *
	 * @param address The address to be set for the location.
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * Retrieves the unique identifier of the associated city.
	 *
	 * @return The city's unique identifier.
	 */
	public int getCityId() {
		return cityId;
	}

	/**
	 * Sets the unique identifier of the associated city.
	 *
	 * @param cityId The city's unique identifier to be set for the location.
	 */
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	/**
	 * Returns a string representation of the Location object.
	 *
	 * @return A string containing the location's details including ID, address, and
	 *         city ID.
	 */
	@Override
	public String toString() {
		return "Location{" + "id=" + id + ", address='" + address + '\'' + ", cityId=" + cityId + '}';
	}
}
