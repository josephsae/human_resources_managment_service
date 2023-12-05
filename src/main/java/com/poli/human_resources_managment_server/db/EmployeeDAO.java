package com.poli.human_resources_managment_server.db;

import com.poli.human_resources_managment_server.model.Employee;
import java.sql.*;

/**
 * Data Access Object for managing employee data in the database. This class
 * provides methods to insert, retrieve, update, remove, and relocate employee
 * information.
 */
public class EmployeeDAO {

	/**
	 * Retrieves an employee from the database by their ID.
	 * 
	 * @param id The ID of the employee to retrieve.
	 * @return An Employee object if found, null otherwise.
	 */
	public Employee getEmployeeById(int id) {
		String sql = "SELECT * FROM EMPLEADOS WHERE empl_ID = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				return new Employee(rs.getInt("empl_ID"), rs.getString("empl_primer_nombre"),
						rs.getString("empl_segundo_nombre"), rs.getString("empl_email"), rs.getDate("empl_fecha_nac"),
						rs.getDouble("empl_sueldo"), rs.getDouble("empl_comision"), rs.getInt("empl_cargo_ID"),
						(Integer) rs.getObject("empl_Gerente_ID"), rs.getInt("empl_dpto_ID"),
						rs.getBoolean("empl_retirado"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Inserts a new employee into the database.
	 * 
	 * @param employee The Employee object containing the data to be inserted.
	 * @return true if the insertion is successful, false otherwise.
	 */
	public boolean insertEmployee(Employee employee) {
		String sql = "INSERT INTO EMPLEADOS (empl_primer_nombre, empl_segundo_nombre, empl_email, empl_fecha_nac, empl_sueldo, empl_comision, empl_cargo_ID, empl_Gerente_ID, empl_dpto_ID, empl_retirado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, employee.getFirstName());
			pstmt.setString(2, employee.getSecondName());
			pstmt.setString(3, employee.getEmail());
			pstmt.setDate(4, new java.sql.Date(employee.getBirthDate().getTime()));
			pstmt.setDouble(5, employee.getSalary());
			if (employee.getCommission() != null) {
				pstmt.setDouble(6, employee.getCommission());
			} else {
				pstmt.setNull(6, Types.DOUBLE);
			}
			pstmt.setInt(7, employee.getPositionId());
			if (employee.getManagerId() != null) {
				pstmt.setInt(8, employee.getManagerId());
			} else {
				pstmt.setNull(8, Types.INTEGER);
			}
			pstmt.setInt(9, employee.getDepartmentId());
			pstmt.setBoolean(10, employee.getRetired());

			int affectedRows = pstmt.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Updates the data of an existing employee in the database.
	 * 
	 * @param employee The Employee object containing the updated data.
	 * @return true if the update is successful, false otherwise.
	 */
	public boolean updateEmployee(Employee employee) {
		String sql = "UPDATE EMPLEADOS SET empl_primer_nombre = ?, empl_segundo_nombre = ?, empl_email = ?, empl_fecha_nac = ?, empl_sueldo = ?, empl_comision = ?, empl_cargo_ID = ?, empl_Gerente_ID = ?, empl_dpto_ID = ? WHERE empl_ID = ?";
		try (Connection conn = DatabaseConnection.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, employee.getFirstName());
			pstmt.setString(2, employee.getSecondName());
			pstmt.setString(3, employee.getEmail());
			pstmt.setDate(4, new java.sql.Date(employee.getBirthDate().getTime()));
			pstmt.setDouble(5, employee.getSalary());
			if (employee.getCommission() != null) {
				pstmt.setDouble(6, employee.getCommission());
			} else {
				pstmt.setNull(6, Types.DOUBLE);
			}
			pstmt.setInt(7, employee.getPositionId());
			if (employee.getManagerId() != null) {
				pstmt.setInt(8, employee.getManagerId());
			} else {
				pstmt.setNull(8, Types.INTEGER);
			}
			pstmt.setInt(9, employee.getDepartmentId());
			pstmt.setInt(10, employee.getId());

			int affectedRows = pstmt.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Removes an employee from the database based on their ID.
	 * 
	 * @param id The ID of the employee to remove.
	 * @return true if the removal is successful, false otherwise.
	 */
	public boolean removeEmployee(int id) {
		Connection conn = null;

		try {
			conn = DatabaseConnection.getConnection();
			conn.setAutoCommit(false);

			String updateSql = "UPDATE EMPLEADOS SET empl_retirado = TRUE WHERE empl_ID = ?";
			try (PreparedStatement pstmt = conn.prepareStatement(updateSql)) {
				pstmt.setInt(1, id);
				pstmt.executeUpdate();
			}

			String insertHistoricoSql = "INSERT INTO HISTORICO (emphist_fecha_retiro, emphist_cargo_ID, emphist_empl_ID, emphist_dpto_ID) SELECT CURDATE(), empl_cargo_ID, empl_ID, empl_dpto_ID FROM EMPLEADOS WHERE empl_ID = ?";
			try (PreparedStatement pstmt = conn.prepareStatement(insertHistoricoSql)) {
				pstmt.setInt(1, id);
				pstmt.executeUpdate();
			}

			conn.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.setAutoCommit(true);
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Relocates an employee to a new department and records the change in the
	 * historical record.
	 * 
	 * @param employeeId      The ID of the employee to relocate.
	 * @param cityName        The name of the city where the new department is
	 *                        located.
	 * @param locationAddress The address of the location within the city.
	 * @param departmentName  The name of the new department.
	 * @return true if the relocation and historical record insertion are
	 *         successful, false otherwise.
	 */
	public boolean relocateEmployee(int employeeId, String cityName, String locationAddress, String departmentName) {
		Connection conn = null;

		try {
			conn = DatabaseConnection.getConnection();
			conn.setAutoCommit(false);

			int cityId = getCityId(conn, cityName);
			int locationId = getLocationId(conn, locationAddress, cityId);
			int departmentId = getDepartmentId(conn, departmentName, locationId);

			if (cityId == -1 || locationId == -1 || departmentId == -1) {
				return false;
			}

			boolean updateSuccess = updateEmployeeDepartment(conn, employeeId, departmentId);
			if (!updateSuccess) {
				return false;
			}

			boolean insertSuccess = insertIntoHistorico(conn, employeeId, departmentId);
			if (!insertSuccess) {
				return false;
			}

			conn.commit();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.setAutoCommit(true);
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private int getCityId(Connection conn, String cityName) throws SQLException {
		String sql = "SELECT ciud_ID FROM CIUDADES WHERE ciud_nombre = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, cityName);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("ciud_ID");
			}
		}
		return -1;
	}

	private int getLocationId(Connection conn, String locationAddress, int cityId) throws SQLException {
		String sql = "SELECT localiz_ID FROM LOCALIZACIONES WHERE localiz_direccion = ? AND localiz_ciudad_ID = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, locationAddress);
			pstmt.setInt(2, cityId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("localiz_ID");
			}
		}
		return -1;
	}

	private int getDepartmentId(Connection conn, String departmentName, int locationId) throws SQLException {
		String sql = "SELECT dpto_ID FROM DEPARTAMENTOS WHERE dpto_nombre = ? AND dpto_localiz_ID = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, departmentName);
			pstmt.setInt(2, locationId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("dpto_ID");
			}
		}
		return -1;
	}

	private boolean updateEmployeeDepartment(Connection conn, int employeeId, int departmentId) throws SQLException {
		String sql = "UPDATE EMPLEADOS SET empl_dpto_ID = ? WHERE empl_ID = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, departmentId);
			pstmt.setInt(2, employeeId);
			int affectedRows = pstmt.executeUpdate();
			return affectedRows > 0;
		}
	}

	private boolean insertIntoHistorico(Connection conn, int employeeId, int departmentId) throws SQLException {
		String sql = "INSERT INTO HISTORICO (emphist_fecha_retiro, emphist_cargo_ID, emphist_empl_ID, emphist_dpto_ID) "
				+ "SELECT CURDATE(), empl_cargo_ID, empl_ID, ? FROM EMPLEADOS WHERE empl_ID = ?";
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, departmentId);
			pstmt.setInt(2, employeeId);
			int affectedRows = pstmt.executeUpdate();
			return affectedRows > 0;
		}
	}

}
