package com.poli.human_resources_managment_server.server;

import com.poli.human_resources_managment_server.server.handlers.CityHandler;
import com.poli.human_resources_managment_server.server.handlers.CountryHandler;
import com.poli.human_resources_managment_server.server.handlers.DepartmentHandler;
import com.poli.human_resources_managment_server.server.handlers.EmployeeHandler;
import com.poli.human_resources_managment_server.server.handlers.JobHandler;
import com.poli.human_resources_managment_server.server.handlers.LocationHandler;
import com.poli.human_resources_managment_server.server.handlers.HistoricHandler;

import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handles client requests in a dedicated thread. This class is responsible for
 * processing incoming messages from the client and delegating them to specific
 * handlers based on the request type.
 */
class ClientHandler extends Thread {
    /**
     * Socket for the client connection. This socket is used to communicate with the connected client.
     */
    private Socket socket;

    /**
     * Logger for logging messages and exceptions related to the client handling process.
     */
    private static final Logger logger = Logger.getLogger(Server.class.getName());

    /**
     * Handler for country-related operations received from the client.
     */
    private CountryHandler countryHandler;

    /**
     * Handler for city-related operations received from the client.
     */
    private CityHandler cityHandler;

    /**
     * Handler for department-related operations received from the client.
     */
    private DepartmentHandler departmentHandler;

    /**
     * Handler for employee-related operations received from the client.
     */
    private EmployeeHandler employeeHandler;

    /**
     * Handler for job-related operations received from the client.
     */
    private JobHandler jobHandler;

    /**
     * Handler for location-related operations received from the client.
     */
    private LocationHandler locationHandler;

    /**
     * Handler for historic data-related operations received from the client.
     */
    private HistoricHandler historicHandler;

	/**
	 * Constructs a new ClientHandler with a given socket. Initializes all the
	 * individual handlers for different request types.
	 *
	 * @param socket The socket through which the server communicates with the
	 *               client.
	 */
	public ClientHandler(Socket socket) {
		this.socket = socket;
		this.countryHandler = new CountryHandler();
		this.cityHandler = new CityHandler();
		this.departmentHandler = new DepartmentHandler();
		this.employeeHandler = new EmployeeHandler();
		this.jobHandler = new JobHandler();
		this.locationHandler = new LocationHandler();
	}

	/**
	 * Runs the thread, reading messages from the client and responding accordingly.
	 * Continuously listens for messages until the "EXIT" command is received, then
	 * closes the connection.
	 */
	public void run() {
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter writer = new PrintWriter(socket.getOutputStream(), true)) {

			String clientMessage;
			while ((clientMessage = reader.readLine()) != null) {
				if (clientMessage.equals("EXIT")) {
					System.out.println("Client requested to close the connection.");
					return;
				}
				String response = handleClientMessage(clientMessage);
				writer.println(response);
			}
		} catch (IOException e) {
			handleError("Server exception", e);
		} finally {
			closeConnection();
		}
	}

	/**
	 * Handles the received message from the client by delegating it to the
	 * appropriate handler.
	 *
	 * @param clientMessage The message received from the client.
	 * @return The response to the client after processing the message.
	 */
	private String handleClientMessage(String clientMessage) {
		if (clientMessage.startsWith("COUNTRY")) {
			return countryHandler.handleRequest(clientMessage);
		}
		if (clientMessage.startsWith("CITY")) {
			return cityHandler.handleRequest(clientMessage);
		}
		if (clientMessage.startsWith("DEPARTMENT")) {
			return departmentHandler.handleRequest(clientMessage);
		}
		if (clientMessage.startsWith("EMPLOYEE")) {
			return employeeHandler.handleRequest(clientMessage);
		}
		if (clientMessage.startsWith("JOB")) {
			return jobHandler.handleRequest(clientMessage);
		}
		if (clientMessage.startsWith("LOCATION")) {
			return locationHandler.handleRequest(clientMessage);
		}
		if (clientMessage.startsWith("HISTORIC")) {
			return historicHandler.handleRequest(clientMessage);
		}
		return "Invalid command";
	}

	/**
	 * Closes the socket connection with the client.
	 */
	private void closeConnection() {
		try {
			if (socket != null) {
				socket.close();
			}
			System.out.println("Connection with client closed.");
		} catch (IOException e) {
			handleError("Error closing connection", e);
		}
	}

	/**
	 * Logs errors that occur during client handling.
	 *
	 * @param message The error message to log.
	 * @param e       The exception that was thrown.
	 */
	public static void handleError(String message, Exception e) {
		logger.log(Level.SEVERE, message, e);
	}
}