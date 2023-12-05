package com.poli.human_resources_managment_server.server;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.poli.human_resources_managment_server.db.DatabaseConnection;

/**
 * The Server class is responsible for initializing and running the server
 * application. It listens for client connections and handles them in separate
 * threads.
 */
public class Server {
	/**
	 * The default server IP address. This is used when the server IP is not
	 * specified by the user.
	 */
	private static final String DEFAULT_SERVER_IP = "localhost";

	/**
	 * BufferedReader for reading input from the console for server configuration.
	 */
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	/**
	 * Logger for logging important messages and exceptions. Used throughout the
	 * class to log messages related to server operation, such as startup, shutdown,
	 * errors, and client connections.
	 */
	private static final Logger logger = Logger.getLogger(Server.class.getName());

	/**
	 * A static Connection object representing the connection to the database. This
	 * connection is used to perform database operations throughout the server's
	 * lifecycle.
	 */
	private static Connection connection;

	/**
	 * Main method to start the server. It establishes a database connection,
	 * configures the server IP and port, and listens for client connections.
	 *
	 * @param args Command-line arguments (not used).
	 */
	public static void main(String[] args) {
		connection = DatabaseConnection.getConnection();
		if (connection == null) {
			logger.severe("Failed to establish database connection.");
			return;
		}

		String serverIp = getServerIp();
		int port = getServerPort();

		try (ServerSocket serverSocket = new ServerSocket(port, 50, InetAddress.getByName(serverIp))) {
			logger.info("Server is listening on " + serverIp + ":" + port);

			while (true) {
				try {
					Socket socket = serverSocket.accept();
					logger.info("New client connected");
					new ClientHandler(socket).start();
				} catch (IOException e) {
					logger.log(Level.SEVERE, "Error accepting client connection", e);
				}
			}
		} catch (IOException e) {
			handleError("Server exception", e);
		} finally {
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
					logger.info("Database connection closed.");
				}
			} catch (SQLException e) {
				handleError("Error closing database connection", e);
			}
		}
	}

	/**
	 * Reads the server IP address from the user input. Defaults to localhost if no
	 * input is provided.
	 *
	 * @return The IP address of the server.
	 */
	private static String getServerIp() {
		System.out.print("Enter the server IP (leave blank for localhost): ");
		try {
			String serverIp = reader.readLine();
			return serverIp.isEmpty() ? DEFAULT_SERVER_IP : serverIp;
		} catch (IOException e) {
			handleError("Error reading server IP", e);
			return DEFAULT_SERVER_IP;
		}
	}

	/**
	 * Reads the server port from the user input.
	 *
	 * @return The port number of the server.
	 */
	private static int getServerPort() {
		System.out.print("Enter the server port: ");
		while (true) {
			try {
				return Integer.parseInt(reader.readLine());
			} catch (IOException e) {
				handleError("Error reading server port", e);
			} catch (NumberFormatException e) {
				logger.warning("Please enter a valid integer number for the port.");
			}
		}
	}

	/**
	 * Logs errors and exceptions that occur during server operation.
	 *
	 * @param message The error message to log.
	 * @param e       The exception that was thrown.
	 */
	public static void handleError(String message, Exception e) {
		logger.log(Level.SEVERE, message, e);
	}
}