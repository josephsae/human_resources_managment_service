package com.poli.human_resources_managment_server.server.handlers;

import com.poli.human_resources_managment_server.db.EmployeeDAO;
import com.poli.human_resources_managment_server.model.Employee;
import java.sql.Date;
import java.util.StringTokenizer;

/**
 * The EmployeeHandler class is responsible for processing requests related to
 * employee records. It interacts with the EmployeeDAO to perform various
 * operations on employee data in the database.
 */
public class EmployeeHandler {

	private final EmployeeDAO employeeDAO;

	/**
	 * Constructs a new EmployeeHandler instance and initializes the associated
	 * EmployeeDAO.
	 */
	public EmployeeHandler() {
		this.employeeDAO = new EmployeeDAO();
	}

	/**
	 * Handles incoming requests related to employee records.
	 *
	 * @param request The request string containing a command and optional
	 *                parameters.
	 * @return A response string based on the executed command.
	 */
	public String handleRequest(String request) {
		StringTokenizer st = new StringTokenizer(request);
		String command = st.nextToken();

		switch (command) {
		case "EMPLOYEE_GET_BY_ID":
			return getEmployeeById(st);
		case "EMPLOYEE_INSERT":
			return insertEmployee(st);
		case "EMPLOYEE_UPDATE":
			return updateEmployee(st);
		case "EMPLOYEE_REMOVE":
			return removeEmployee(st);
		case "EMPLOYEE_RELOCATE":
			return relocateEmployee(st);
		default:
			return "Invalid employee command";
		}
	}

	/**
	 * Retrieves an employee record by its unique identifier.
	 *
	 * @param st A StringTokenizer containing the command and parameters.
	 * @return A string representation of the employee record if found, or an error
	 *         message if not found.
	 */
	private String getEmployeeById(StringTokenizer st) {
		int id = Integer.parseInt(st.nextToken());
		Employee employee = employeeDAO.getEmployeeById(id);
		return employee != null ? employee.toString() : "Employee not found";
	}

	/**
	 * Inserts a new employee record into the database.
	 *
	 * @param st A StringTokenizer containing the command and parameters.
	 * @return A success message if the insertion is successful, or an error message
	 *         if it fails.
	 */
	private String insertEmployee(StringTokenizer st) {
		Employee employee = parseEmployee(st, false);
		boolean success = employeeDAO.insertEmployee(employee);
		return success ? "Employee inserted successfully" : "Failed to insert employee";
	}

	/**
	 * Updates an existing employee record in the database.
	 *
	 * @param st A StringTokenizer containing the command and parameters.
	 * @return A success message if the update is successful, or an error message if
	 *         it fails.
	 */
	private String updateEmployee(StringTokenizer st) {
		Employee employee = parseEmployee(st, true);
		boolean success = employeeDAO.updateEmployee(employee);
		return success ? "Employee updated successfully" : "Failed to update employee";
	}

	/**
	 * Removes an employee record from the database.
	 *
	 * @param st A StringTokenizer containing the command and parameters.
	 * @return A success message if the deletion is successful, or an error message
	 *         if it fails.
	 */
	private String removeEmployee(StringTokenizer st) {
		int id = Integer.parseInt(st.nextToken());
		boolean success = employeeDAO.removeEmployee(id);
		return success ? "Employee deleted successfully" : "Failed to delete employee";
	}

	/**
	 * Relocates an employee to a different city, location, and department.
	 *
	 * @param st A StringTokenizer containing the command and parameters.
	 * @return A success message if the relocation is successful, or an error
	 *         message if it fails.
	 */
	private String relocateEmployee(StringTokenizer st) {
		int employeeId = Integer.parseInt(st.nextToken());
		String cityName = st.nextToken();
		st.nextToken("\"");
		String locationAddress = st.nextToken("\"");
		String departmentName = st.nextToken();
		boolean success = employeeDAO.relocateEmployee(employeeId, cityName, locationAddress, departmentName);
		return success ? "Employee relocated successfully" : "Failed to relocate employee";
	}

	/**
	 * Parses the employee information from the StringTokenizer.
	 *
	 * @param st       A StringTokenizer containing employee information.
	 * @param isUpdate A boolean indicating whether it's an update operation.
	 * @return An Employee object with the parsed information.
	 */
	private Employee parseEmployee(StringTokenizer st, boolean isUpdate) {
		Integer id = isUpdate ? Integer.valueOf(st.nextToken()) : null;
		String firstName = st.nextToken();
		String secondName = st.hasMoreTokens() ? st.nextToken() : null;
		if ("null".equalsIgnoreCase(secondName)) {
			secondName = null;
		}
		String email = st.nextToken();
		Date birthDate = Date.valueOf(st.nextToken());
		double salary = Double.parseDouble(st.nextToken());
		Double commission = null;
		if (st.hasMoreTokens()) {
			String commissionToken = st.nextToken();
			commission = "null".equalsIgnoreCase(commissionToken) ? null : Double.valueOf(commissionToken);
		}
		int positionId = Integer.parseInt(st.nextToken());
		Integer managerId = null;
		if (st.hasMoreTokens()) {
			String managerIdToken = st.nextToken();
			managerId = "null".equalsIgnoreCase(managerIdToken) ? null : Integer.valueOf(managerIdToken);
		}
		int departmentId = Integer.parseInt(st.nextToken());
		boolean retired = false;

		return new Employee(id, firstName, secondName, email, birthDate, salary, commission, positionId, managerId,
				departmentId, retired);
	}
}
