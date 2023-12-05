package com.poli.human_resources_managment_server.server.handlers;

import com.poli.human_resources_managment_server.db.HistoricDAO;
import com.poli.human_resources_managment_server.model.Historic;
import java.util.StringTokenizer;

/**
 * The HistoricHandler class is responsible for processing requests related to
 * historic records. It interacts with the HistoricDAO to retrieve historic data
 * from the database.
 */
public class HistoricHandler {

	private final HistoricDAO historicDAO;

	/**
	 * Constructs a new HistoricHandler instance and initializes the associated
	 * HistoricDAO.
	 */
	public HistoricHandler() {
		this.historicDAO = new HistoricDAO();
	}

	/**
	 * Handles incoming requests related to historic records.
	 *
	 * @param request The request string containing a command and optional
	 *                parameters.
	 * @return A response string based on the executed command.
	 */
	public String handleRequest(String request) {
		StringTokenizer st = new StringTokenizer(request);
		String command = st.nextToken();

		switch (command) {
		case "HISTORIC_GET_BY_ID":
			return getHistoricById(st);
		default:
			return "Invalid historic command";
		}
	}

	/**
	 * Retrieves a historic record by its unique identifier.
	 *
	 * @param st A StringTokenizer containing the command and parameters.
	 * @return A string representation of the historic record if found, or an error
	 *         message if not found.
	 */
	private String getHistoricById(StringTokenizer st) {
		int id = Integer.parseInt(st.nextToken());
		Historic historic = historicDAO.getHistoricById(id);
		return historic != null ? historic.toString() : "Historic record not found";
	}
}
