package com.poli.human_resources_managment_server.model;

/**
 * Represents a job position within the organization. This class encapsulates
 * the details of a job, including its unique identifier, name, and salary
 * range.
 */
public class Job {
	private int id;
	private String name;
	private double salaryMin;
	private double salaryMax;

	/**
	 * Constructs a new Job instance without an ID, typically used for inserting new
	 * job positions.
	 *
	 * @param name      The name of the job position.
	 * @param salaryMin The minimum salary for the job position.
	 * @param salaryMax The maximum salary for the job position.
	 */
	public Job(String name, double salaryMin, double salaryMax) {
		this.name = name;
		this.salaryMin = salaryMin;
		this.salaryMax = salaryMax;
	}

	/**
	 * Constructs a new Job instance with an ID, typically used when retrieving data
	 * from the database.
	 *
	 * @param id        The unique identifier of the job position.
	 * @param name      The name of the job position.
	 * @param salaryMin The minimum salary for the job position.
	 * @param salaryMax The maximum salary for the job position.
	 */
	public Job(int id, String name, double salaryMin, double salaryMax) {
		this.id = id;
		this.name = name;
		this.salaryMin = salaryMin;
		this.salaryMax = salaryMax;
	}

	/**
	 * Retrieves the unique identifier of the job position.
	 *
	 * @return The identifier of the job position.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the unique identifier of the job position.
	 *
	 * @param id The identifier to be set for the job position.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Retrieves the name of the job position.
	 *
	 * @return The name of the job position.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the job position.
	 *
	 * @param name The name to be set for the job position.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieves the minimum salary for the job position.
	 *
	 * @return The minimum salary for the job position.
	 */
	public double getSalaryMin() {
		return salaryMin;
	}

	/**
	 * Sets the minimum salary for the job position.
	 *
	 * @param salaryMin The minimum salary to be set for the job position.
	 */
	public void setSalaryMin(double salaryMin) {
		this.salaryMin = salaryMin;
	}

	/**
	 * Retrieves the maximum salary for the job position.
	 *
	 * @return The maximum salary for the job position.
	 */
	public double getSalaryMax() {
		return salaryMax;
	}

	/**
	 * Sets the maximum salary for the job position.
	 *
	 * @param salaryMax The maximum salary to be set for the job position.
	 */
	public void setSalaryMax(double salaryMax) {
		this.salaryMax = salaryMax;
	}

	/**
	 * Returns a string representation of the Job object.
	 *
	 * @return A string containing the job's details including ID, name, and salary
	 *         range.
	 */
	@Override
	public String toString() {
		return "Job{" + "id=" + id + ", name='" + name + '\'' + ", salaryMin=" + salaryMin + ", salaryMax=" + salaryMax
				+ '}';
	}
}
