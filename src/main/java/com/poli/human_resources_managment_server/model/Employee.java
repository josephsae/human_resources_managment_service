package com.poli.human_resources_managment_server.model;

import java.util.Date;

/**
 * Represents an employee in the system. This class contains details about the
 * employee, including identification, personal information, job-related data,
 * and status.
 */
public class Employee {
	private Integer id;
	private String firstName;
	private String secondName;
	private String email;
	private Date birthDate;
	private double salary;
	private Double commission;
	private int positionId;
	private Integer managerId;
	private int departmentId;
	private boolean retired;

	/**
	 * Constructs a new Employee instance with the specified details.
	 *
	 * @param id           The unique identifier of the employee.
	 * @param firstName    The first name of the employee.
	 * @param secondName   The second name of the employee. Can be null.
	 * @param email        The email address of the employee.
	 * @param birthDate    The birth date of the employee.
	 * @param salary       The salary of the employee.
	 * @param commission   The commission of the employee. Can be null.
	 * @param positionId   The position ID associated with the employee.
	 * @param managerId    The manager's ID of the employee. Can be null if no
	 *                     manager.
	 * @param departmentId The department ID where the employee works.
	 * @param retired      The retirement status of the employee.
	 */
	public Employee(Integer id, String firstName, String secondName, String email, Date birthDate, double salary,
			Double commission, int positionId, Integer managerId, int departmentId, boolean retired) {
		this.setId(id);
		this.setFirstName(firstName);
		this.setSecondName(secondName);
		this.setEmail(email);
		this.setBirthDate(birthDate);
		this.setSalary(salary);
		this.setCommission(commission);
		this.setPositionId(positionId);
		this.setManagerId(managerId);
		this.setDepartmentId(departmentId);
		this.serRetired(retired);
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Double getCommission() {
		return commission;
	}

	public void setCommission(Double commission) {
		this.commission = commission;
	}

	public int getPositionId() {
		return positionId;
	}

	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public boolean getRetired() {
		return retired;
	}

	public void serRetired(boolean retired) {
		this.retired = retired;
	}

	/**
	 * Returns a string representation of the Employee object.
	 * 
	 * @return A string containing the employee's details including ID, names,
	 *         email, birth date, salary, commission, position ID, manager ID,
	 *         department ID, and retirement status.
	 */
	@Override
	public String toString() {
		return "Employee{" + "id=" + id + ", firstName='" + firstName + '\'' + ", secondName='" + secondName + '\''
				+ ", email='" + email + '\'' + ", birthDate=" + birthDate + ", salary=" + salary + ", commission="
				+ commission + ", positionId=" + positionId + ", managerId=" + managerId + ", departmentId="
				+ departmentId + ", retired=" + retired + "}";
	}

}
