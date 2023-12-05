package com.poli.human_resources_managment_server.db;

import java.sql.*;

import com.poli.human_resources_managment_server.model.Department;

/**
 * Data Access Object for managing department data in the database. This class
 * provides methods to insert, retrieve, and update department information in
 * the database.
 */
public class DepartmentDAO {

	/**
	 * Inserts a new department into the database.
	 * 
	 * @param department The Department object containing the data to be inserted.
	 * @return true if the insertion is successful, false otherwise.
	 */
	public boolean insertDepartment(Department department) {
		String sql = "INSERT INTO DEPARTAMENTOS (dpto_nombre, dpto_localiz_ID) VALUES (?, ?)";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, department.getName());
			preparedStatement.setInt(2, department.getLocationId());
			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Retrieves a department from the database by its ID.
	 * 
	 * @param id The ID of the department to retrieve.
	 * @return A Department object if found, null otherwise.
	 */
	public Department getDepartmentById(int id) {
		String sql = "SELECT * FROM DEPARTAMENTOS WHERE dpto_ID = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				String name = rs.getString("dpto_nombre");
				int locationId = rs.getInt("dpto_localiz_ID");
				return new Department(id, name, locationId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Updates the data of an existing department in the database.
	 * 
	 * @param department The Department object containing the updated data.
	 * @return true if the update is successful, false otherwise.
	 */
	public boolean updateDepartment(Department department) {
		String sql = "UPDATE DEPARTAMENTOS SET dpto_nombre = ?, dpto_localiz_ID = ? WHERE dpto_ID = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, department.getName());
			preparedStatement.setInt(2, department.getLocationId());
			preparedStatement.setInt(3, department.getId());
			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
