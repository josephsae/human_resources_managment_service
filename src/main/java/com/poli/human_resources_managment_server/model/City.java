package com.poli.human_resources_managment_server.model;

/**
 * Represents a city entity in the system. This class holds information about a
 * city, including its ID, name, and the ID of the country it belongs to.
 */
public class City {
	private int id;
	private String name;
	private int countryId;

	/**
	 * Constructs a new City instance without an ID. This constructor can be used
	 * when creating a new city record before its ID is assigned.
	 *
	 * @param name      The name of the city.
	 * @param countryId The ID of the country where the city is located.
	 */
	public City(String name, int countryId) {
		this.name = name;
		this.countryId = countryId;
	}

	/**
	 * Constructs a new City instance with an ID. This constructor is typically used
	 * when retrieving city data from the database.
	 *
	 * @param id        The ID of the city.
	 * @param name      The name of the city.
	 * @param countryId The ID of the country where the city is located.
	 */
	public City(int id, String name, int countryId) {
		this.id = id;
		this.name = name;
		this.countryId = countryId;
	}

	/**
	 * Gets the ID of the city.
	 * 
	 * @return The ID of the city.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the ID of the city.
	 * 
	 * @param id The new ID of the city.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the name of the city.
	 * 
	 * @return The name of the city.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the city.
	 * 
	 * @param name The new name of the city.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the ID of the country where the city is located.
	 * 
	 * @return The ID of the country.
	 */
	public int getCountryId() {
		return countryId;
	}

	/**
	 * Sets the ID of the country where the city is located.
	 * 
	 * @param countryId The new ID of the country.
	 */
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}

	/**
	 * Returns a string representation of the City object.
	 * 
	 * @return A string containing the ID, name, and country ID of the city.
	 */
	@Override
	public String toString() {
		return "City{" + "id=" + id + ", name='" + name + '\'' + ", countryId=" + countryId + '}';
	}
}