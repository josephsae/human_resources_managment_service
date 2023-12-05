package com.poli.human_resources_managment_server.server.handlers;

import com.poli.human_resources_managment_server.db.JobDAO;
import com.poli.human_resources_managment_server.model.Job;
import java.util.StringTokenizer;

/**
 * The JobHandler class is responsible for processing requests related to job
 * records. It interacts with the JobDAO to perform various operations on job
 * data in the database.
 */
public class JobHandler {

	private final JobDAO jobDAO;

	/**
	 * Constructs a new JobHandler instance and initializes the associated JobDAO.
	 */
	public JobHandler() {
		this.jobDAO = new JobDAO();
	}

	/**
	 * Handles incoming requests related to job records.
	 *
	 * @param request The request string containing a command and optional
	 *                parameters.
	 * @return A response string based on the executed command.
	 */
	public String handleRequest(String request) {
		StringTokenizer st = new StringTokenizer(request);
		String command = st.nextToken();

		switch (command) {
		case "JOB_GET_BY_ID":
			return getJobById(st);
		case "JOB_INSERT":
			return insertJob(st);
		case "JOB_UPDATE":
			return updateJob(st);
		default:
			return "Invalid job command";
		}
	}

	/**
	 * Retrieves a job record by its unique identifier.
	 *
	 * @param st A StringTokenizer containing the command and parameters.
	 * @return A string representation of the job record if found, or an error
	 *         message if not found.
	 */
	private String getJobById(StringTokenizer st) {
		int id = Integer.parseInt(st.nextToken());
		Job job = jobDAO.getJobById(id);
		return job != null ? job.toString() : "Job not found";
	}

	/**
	 * Inserts a new job record into the database.
	 *
	 * @param st A StringTokenizer containing the command and parameters.
	 * @return A success message if the insertion is successful, or an error message
	 *         if it fails.
	 */
	private String insertJob(StringTokenizer st) {
		String name = st.nextToken();
		double minSalary = Double.parseDouble(st.nextToken());
		double maxSalary = Double.parseDouble(st.nextToken());
		boolean success = jobDAO.insertJob(new Job(name, minSalary, maxSalary));
		return success ? "Job inserted successfully" : "Failed to insert job";
	}

	/**
	 * Updates an existing job record in the database.
	 *
	 * @param st A StringTokenizer containing the command and parameters.
	 * @return A success message if the update is successful, or an error message if
	 *         it fails.
	 */
	private String updateJob(StringTokenizer st) {
		int id = Integer.parseInt(st.nextToken());
		String newName = st.nextToken();
		double newMinSalary = Double.parseDouble(st.nextToken());
		double newMaxSalary = Double.parseDouble(st.nextToken());
		boolean success = jobDAO.updateJob(new Job(id, newName, newMinSalary, newMaxSalary));
		return success ? "Job updated successfully" : "Failed to update job";
	}

}
