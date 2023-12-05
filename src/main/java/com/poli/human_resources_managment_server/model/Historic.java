package com.poli.human_resources_managment_server.model;

import java.util.Date;

/**
 * Represents a historical record of an employee's career events in the system.
 * This class holds information about a historical event, including the ID of
 * the record, the date of retirement, job ID, employee ID, and department ID.
 */
public class Historic {
	private int id;
	private Date retirementDate;
	private int jobId;
	private int employeeId;
	private int departmentId;

	/**
	 * Constructs a new Historic instance. This constructor is used when creating a
	 * historical record with all its details.
	 *
	 * @param id             The ID of the historical record.
	 * @param retirementDate The date of the employee's retirement.
	 * @param jobId          The ID of the job associated with this record.
	 * @param employeeId     The ID of the employee associated with this record.
	 * @param departmentId   The ID of the department associated with this record.
	 */
	public Historic(int id, Date retirementDate, int jobId, int employeeId, int departmentId) {
		this.id = id;
		this.retirementDate = retirementDate;
		this.jobId = jobId;
		this.employeeId = employeeId;
		this.departmentId = departmentId;
	}

	// Getters and setters

	/**
	 * Gets the ID of the historical record.
	 * 
	 * @return The ID of the historical record.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the ID of the historical record.
	 * 
	 * @param id The new ID of the historical record.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the retirement date of the employee.
	 * 
	 * @return The retirement date.
	 */
	public Date getRetirementDate() {
		return retirementDate;
	}

	/**
	 * Sets the retirement date of the employee.
	 * 
	 * @param retirementDate The new retirement date.
	 */
	public void setRetirementDate(Date retirementDate) {
		this.retirementDate = retirementDate;
	}

	/**
	 * Gets the job ID associated with this historical record.
	 * 
	 * @return The job ID.
	 */
	public int getJobId() {
		return jobId;
	}

	/**
	 * Sets the job ID associated with this historical record.
	 * 
	 * @param jobId The new job ID.
	 */
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	/**
	 * Gets the employee ID associated with this historical record.
	 * 
	 * @return The employee ID.
	 */
	public int getEmployeeId() {
		return employeeId;
	}

	/**
	 * Sets the employee ID associated with this historical record.
	 * 
	 * @param employeeId The new employee ID.
	 */
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * Gets the department ID associated with this historical record.
	 * 
	 * @return The department ID.
	 */
	public int getDepartmentId() {
		return departmentId;
	}

	/**
	 * Sets the department ID associated with this historical record.
	 * 
	 * @param departmentId The new department ID.
	 */
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	/**
	 * Returns a string representation of the Historic object.
	 * 
	 * @return A string containing the ID, retirement date, job ID, employee ID, and
	 *         department ID.
	 */
	@Override
	public String toString() {
		return "Historic{" + "id=" + id + ", retirementDate=" + retirementDate + ", jobId=" + jobId + ", employeeId="
				+ employeeId + ", departmentId=" + departmentId + '}';
	}
}
