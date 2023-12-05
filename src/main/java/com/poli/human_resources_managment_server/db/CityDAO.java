package com.poli.human_resources_managment_server.db;

import com.poli.human_resources_managment_server.model.City;
import java.sql.*;

/**
 * Data Access Object (DAO) class for handling database operations related to
 * cities. This class provides methods to insert, retrieve, and update city data
 * in the database.
 */
public class CityDAO {

	/**
	 * Inserts a new city into the database.
	 *
	 * @param city The city object to be inserted.
	 * @return true if the insert operation is successful, false otherwise.
	 */
	public boolean insertCity(City city) {
		String sql = "INSERT INTO CIUDADES (ciud_nombre, ciud_pais_ID) VALUES (?, ?)";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, city.getName());
			preparedStatement.setInt(2, city.getCountryId());
			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Retrieves a city by its ID.
	 *
	 * @param id The ID of the city to retrieve.
	 * @return The City object if found, null otherwise.
	 */
	public City getCityById(int id) {
		String sql = "SELECT * FROM CIUDADES WHERE ciud_ID = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				String name = rs.getString("ciud_nombre");
				int countryId = rs.getInt("ciud_pais_ID");
				return new City(id, name, countryId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Updates the details of an existing city in the database.
	 *
	 * @param city The city object with updated values.
	 * @return true if the update operation is successful, false otherwise.
	 */
	public boolean updateCity(City city) {
		String sql = "UPDATE CIUDADES SET ciud_nombre = ?, ciud_pais_ID = ? WHERE ciud_ID = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, city.getName());
			preparedStatement.setInt(2, city.getCountryId());
			preparedStatement.setInt(3, city.getId());
			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
