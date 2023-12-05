package com.poli.human_resources_managment_server.server.handlers;

import com.poli.human_resources_managment_server.db.DepartmentDAO;
import com.poli.human_resources_managment_server.model.Department;
import java.util.StringTokenizer;

/**
 * The DepartmentHandler class is responsible for processing requests related to
 * department records. It interacts with the DepartmentDAO to perform various
 * operations on department data in the database.
 */
public class DepartmentHandler {

	private final DepartmentDAO departmentDAO;

	/**
	 * Constructs a new DepartmentHandler instance and initializes the associated
	 * DepartmentDAO.
	 */
	public DepartmentHandler() {
		this.departmentDAO = new DepartmentDAO();
	}

	/**
	 * Handles incoming requests related to department records.
	 *
	 * @param request The request string containing a command and optional
	 *                parameters.
	 * @return A response string based on the executed command.
	 */
	public String handleRequest(String request) {
		StringTokenizer st = new StringTokenizer(request);
		String command = st.nextToken();

		switch (command) {
		case "DEPARTMENT_GET_BY_ID":
			return getDepartmentById(st);
		case "DEPARTMENT_INSERT":
			return insertDepartment(st);
		case "DEPARTMENT_UPDATE":
			return updateDepartment(st);
		default:
			return "Invalid department command";
		}
	}

	/**
	 * Retrieves a department record by its unique identifier.
	 *
	 * @param st A StringTokenizer containing the command and parameters.
	 * @return A string representation of the department record if found, or an
	 *         error message if not found.
	 */
	private String getDepartmentById(StringTokenizer st) {
		int id = Integer.parseInt(st.nextToken());
		Department department = departmentDAO.getDepartmentById(id);
		return department != null ? department.toString() : "Department not found";
	}

	/**
	 * Inserts a new department record into the database.
	 *
	 * @param st A StringTokenizer containing the command and parameters.
	 * @return A success message if the insertion is successful, or an error message
	 *         if it fails.
	 */
	private String insertDepartment(StringTokenizer st) {
		String name = st.nextToken();
		int locationId = Integer.parseInt(st.nextToken());
		boolean success = departmentDAO.insertDepartment(new Department(name, locationId));
		return success ? "Department inserted successfully" : "Failed to insert department";
	}

	/**
	 * Updates an existing department record in the database.
	 *
	 * @param st A StringTokenizer containing the command and parameters.
	 * @return A success message if the update is successful, or an error message if
	 *         it fails.
	 */
	private String updateDepartment(StringTokenizer st) {
		int id = Integer.parseInt(st.nextToken());
		String newName = st.nextToken();
		int newLocationId = Integer.parseInt(st.nextToken());
		boolean success = departmentDAO.updateDepartment(new Department(id, newName, newLocationId));
		return success ? "Department updated successfully" : "Failed to update department";
	}

}
