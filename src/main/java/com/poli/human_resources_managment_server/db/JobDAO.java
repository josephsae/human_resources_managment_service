package com.poli.human_resources_managment_server.db;

import java.sql.*;

import com.poli.human_resources_managment_server.model.Job;

/**
 * Data Access Object for managing job records in the database. Provides methods
 * to insert, retrieve, and update job information.
 */
public class JobDAO {

	/**
	 * Inserts a new job record into the database.
	 * 
	 * @param job The Job object containing the data to be inserted.
	 * @return true if the operation was successful, false otherwise.
	 * @throws SQLException If a database access error occurs.
	 */
	public boolean insertJob(Job job) {
		String sql = "INSERT INTO CARGOS (cargo_nombre, cargo_sueldo_minimo, cargo_sueldo_maximo) VALUES (?, ?, ?)";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, job.getName());
			preparedStatement.setDouble(2, job.getSalaryMin());
			preparedStatement.setDouble(3, job.getSalaryMax());
			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Retrieves a job record from the database by its ID.
	 * 
	 * @param id The ID of the job record to retrieve.
	 * @return A Job object containing the data if found, null otherwise.
	 * @throws SQLException If a database access error occurs.
	 */
	public Job getJobById(int id) {
		String sql = "SELECT * FROM CARGOS WHERE cargo_ID = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				String name = rs.getString("cargo_nombre");
				double salaryMin = rs.getDouble("cargo_sueldo_minimo");
				double salaryMax = rs.getDouble("cargo_sueldo_maximo");
				return new Job(id, name, salaryMin, salaryMax);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Updates an existing job record in the database.
	 * 
	 * @param job The Job object containing the updated data.
	 * @return true if the operation was successful, false otherwise.
	 * @throws SQLException If a database access error occurs.
	 */
	public boolean updateJob(Job job) {
		String sql = "UPDATE CARGOS SET cargo_nombre = ?, cargo_sueldo_minimo = ?, cargo_sueldo_maximo = ? WHERE cargo_ID = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, job.getName());
			preparedStatement.setDouble(2, job.getSalaryMin());
			preparedStatement.setDouble(3, job.getSalaryMax());
			preparedStatement.setInt(4, job.getId());
			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
