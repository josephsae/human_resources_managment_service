package com.poli.human_resources_managment_server.db;

import com.poli.human_resources_managment_server.model.Historic;
import java.sql.*;

/**
 * Data Access Object for managing historic records in the database. Provides
 * methods to retrieve historic data.
 */
public class HistoricDAO {

	/**
	 * Retrieves a historic record from the database by its ID.
	 * 
	 * @param id The ID of the historic record to retrieve.
	 * @return A Historic object containing the data if found, null otherwise.
	 * @throws SQLException If a database access error occurs or the URL is null.
	 */
	public Historic getHistoricById(int id) {
		String sql = "SELECT * FROM historico WHERE emphist_ID = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return new Historic(rs.getInt("emphist_ID"), rs.getDate("emphist_fecha_retiro"),
						rs.getInt("emphist_cargo_ID"), rs.getInt("emphist_empl_ID"), rs.getInt("emphist_dpto_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
