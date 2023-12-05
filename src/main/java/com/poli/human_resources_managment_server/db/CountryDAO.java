package com.poli.human_resources_managment_server.db;

import com.poli.human_resources_managment_server.model.Country;
import java.sql.*;

/**
 * Data Access Object for managing country data in the database. This class
 * provides methods to insert, retrieve, and update country information in the
 * database.
 */
public class CountryDAO {

	/**
	 * Inserts a new country into the database.
	 * 
	 * @param countryName The name of the country to insert.
	 * @return true if the insertion is successful, false otherwise.
	 */
	public boolean insertCountry(String countryName) {
		String sql = "INSERT INTO PAISES (pais_nombre) VALUES (?)";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, countryName);
			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Retrieves a country from the database by its ID.
	 * 
	 * @param id The ID of the country to retrieve.
	 * @return A Country object if found, null otherwise.
	 */
	public Country getCountryById(int id) {
		String sql = "SELECT * FROM PAISES WHERE pais_ID = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				String name = rs.getString("pais_nombre");
				return new Country(id, name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Updates the name of an existing country in the database.
	 * 
	 * @param id      The ID of the country to update.
	 * @param newName The new name for the country.
	 * @return true if the update is successful, false otherwise.
	 */
	public boolean updateCountry(int id, String newName) {
		String sql = "UPDATE PAISES SET pais_nombre = ? WHERE pais_ID = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, newName);
			preparedStatement.setInt(2, id);
			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
