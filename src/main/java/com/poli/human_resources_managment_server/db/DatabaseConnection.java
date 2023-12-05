package com.poli.human_resources_managment_server.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Provides a method to establish a connection to the database using the database configuration properties.
 */
public class DatabaseConnection {

    /**
     * Establishes and returns a connection to the database. The database connection
     * parameters (URL, user, password) are read from the 'dbconfig.properties' file.
     *
     * @return A {@link Connection} object to the database, or null if a connection
     *         cannot be established.
     */
	public static Connection getConnection() {
		try (InputStream input = DatabaseConnection.class.getClassLoader().getResourceAsStream("dbconfig.properties")) {
			Properties prop = new Properties();

			if (input == null) {
				System.out.println("Sorry, unable to find dbconfig.properties");
				return null;
			}

			prop.load(input);

			String url = prop.getProperty("db.url");
			String user = prop.getProperty("db.user");
			String password = prop.getProperty("db.password");

			return DriverManager.getConnection(url, user, password);
		} catch (IOException | SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}
}