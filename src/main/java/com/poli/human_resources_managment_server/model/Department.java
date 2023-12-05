package com.poli.human_resources_managment_server.model;

/**
 * Represents a department within the organization. This class encapsulates the
 * details of a department, including its unique identifier, name, and the
 * location ID where it is situated.
 */
public class Department {
	private int id;
	private String name;
	private int locationId;

	/**
	 * Constructs a new Department instance without an ID, typically used for
	 * inserting new departments.
	 *
	 * @param name       The name of the department.
	 * @param locationId The unique identifier of the location where the department
	 *                   is situated.
	 */
	public Department(String name, int locationId) {
		this.name = name;
		this.locationId = locationId;
	}

	/**
	 * Constructs a new Department instance with an ID, typically used when
	 * retrieving data from the database.
	 *
	 * @param id         The unique identifier of the department.
	 * @param name       The name of the department.
	 * @param locationId The unique identifier of the location where the department
	 *                   is situated.
	 */
	public Department(int id, String name, int locationId) {
		this.id = id;
		this.name = name;
		this.locationId = locationId;
	}

	/**
	 * Retrieves the unique identifier of the department.
	 *
	 * @return The identifier of the department.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the unique identifier of the department.
	 *
	 * @param id The identifier to be set for the department.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retrieves the name of the department.
	 *
	 * @return The name of the department.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the department.
	 *
	 * @param name The name to be set for the department.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieves the identifier of the location where the department is situated.
	 *
	 * @return The identifier of the location.
	 */
	public int getLocationId() {
		return locationId;
	}

	/**
	 * Sets the identifier of the location where the department is situated.
	 *
	 * @param locationId The location identifier to be set for the department.
	 */
	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	/**
	 * Returns a string representation of the Department object.
	 *
	 * @return A string containing the department's details including ID, name, and
	 *         location ID.
	 */
	@Override
	public String toString() {
		return "Department{" + "id=" + id + ", name='" + name + '\'' + ", locationId=" + locationId + '}';
	}
}
