package com.poli.human_resources_managment_server.db;

import com.poli.human_resources_managment_server.model.Location;
import java.sql.*;

/**
 * Data Access Object for managing location records in the database. Provides
 * methods to insert, retrieve, and update location information.
 */
public class LocationDAO {

	/**
	 * Inserts a new location record into the database.
	 * 
	 * @param location The Location object containing the data to be inserted.
	 * @return true if the operation was successful, false otherwise.
	 * @throws SQLException If a database access error occurs.
	 */
	public boolean insertLocation(Location location) {
		String sql = "INSERT INTO LOCALIZACIONES (localiz_direccion, localiz_ciudad_ID) VALUES (?, ?)";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, location.getAddress());
			preparedStatement.setInt(2, location.getCityId());
			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Retrieves a location record from the database by its ID.
	 * 
	 * @param id The ID of the location record to retrieve.
	 * @return A Location object containing the data if found, null otherwise.
	 * @throws SQLException If a database access error occurs.
	 */
	public Location getLocationById(int id) {
		String sql = "SELECT * FROM LOCALIZACIONES WHERE localiz_ID = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				String address = rs.getString("localiz_direccion");
				int cityId = rs.getInt("localiz_ciudad_ID");
				return new Location(id, address, cityId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Updates an existing location record in the database.
	 * 
	 * @param location The Location object containing the updated data.
	 * @return true if the operation was successful, false otherwise.
	 * @throws SQLException If a database access error occurs.
	 */
	public boolean updateLocation(Location location) {
		String sql = "UPDATE LOCALIZACIONES SET localiz_direccion = ?, localiz_ciudad_ID = ? WHERE localiz_ID = ?";
		try (Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setString(1, location.getAddress());
			preparedStatement.setInt(2, location.getCityId());
			preparedStatement.setInt(3, location.getId());
			return preparedStatement.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

}
