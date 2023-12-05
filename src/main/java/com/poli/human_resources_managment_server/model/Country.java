package com.poli.human_resources_managment_server.model;

/**
 * Represents a country in the system. This class contains the unique identifier
 * and name of a country.
 */
public class Country {
	private int id;
	private String name;

	/**
	 * Constructs a new Country instance with the specified details.
	 *
	 * @param id   The unique identifier of the country.
	 * @param name The name of the country.
	 */
	public Country(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * Retrieves the unique identifier of the country.
	 *
	 * @return The identifier of the country.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the unique identifier of the country.
	 *
	 * @param id The identifier to be set for the country.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retrieves the name of the country.
	 *
	 * @return The name of the country.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the country.
	 *
	 * @param name The name to be set for the country.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns a string representation of the Country object.
	 * 
	 * @return A string containing the country's details including ID and name.
	 */
	@Override
	public String toString() {
		return "Country{" + "id=" + id + ", name='" + name + '\'' + '}';
	}
}
