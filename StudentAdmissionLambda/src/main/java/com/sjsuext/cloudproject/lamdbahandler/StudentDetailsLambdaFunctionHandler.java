package com.sjsuext.cloudproject.lamdbahandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang.RandomStringUtils;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.sjsuext.cloudproject.model.StudentAPIRequest;
import com.sjsuext.cloudproject.model.StudentDetails;
import com.sjsuext.cloudproject.util.DatabaseConnection;
import com.sjsuext.cloudproject.util.Utilities;

public class StudentDetailsLambdaFunctionHandler implements RequestHandler<StudentAPIRequest, StudentDetails> {

	private Connection connection;

	@Override
	public StudentDetails handleRequest(StudentAPIRequest input, Context context) {

		StudentDetails studentDetails = null;
		LambdaLogger logger = context.getLogger();
		connection = DatabaseConnection.getRemoteConnection();

		switch (input.getHttpMethod()) {

		case "GET":
			logger.log("Inside GET");
			logger.log("student " + input);
			logger.log("before call " + input.getUsername());
			studentDetails = getStudentDetails(input.getUsername());
			logger.log("GET Completed");
			break;

		case "POST":
			if (postStudentDetails(input.getStudent())) {
				studentDetails = input.getStudent();
				logger.log("POST Completed");
			}
		}

		return studentDetails;
	}

	private StudentDetails getStudentDetails(String username) {
		System.out.println("username " + username);
		StudentDetails studentDetails = null;
		try {
			PreparedStatement prepareStatement = connection
					.prepareStatement("select * from \"StudentDetailsTable\" where username = ?");
			prepareStatement.setString(1, username);
			ResultSet rs = prepareStatement.executeQuery();

			if (rs.next()) {
				studentDetails = new StudentDetails();
				studentDetails.setId(Integer.parseInt(rs.getString("id")));
				studentDetails.setFirstName(rs.getString("firstName"));
				studentDetails.setLastName(rs.getString("lastName"));
				studentDetails.setUsername(rs.getString("username"));
				studentDetails.setDateOfBirth(Utilities.convertStringToDate(rs.getString("dateOfBirth")));
				studentDetails.setGender(rs.getString("gender"));
				studentDetails.setPrograms(rs.getString("programs"));
				studentDetails.setAbout(rs.getString("about"));
				studentDetails.setName(rs.getString("name"));
				studentDetails.setFileUploadTime(Utilities.convertStringToDate(rs.getString("fileUploadTime")));
				studentDetails.setFileDesc(rs.getString("fileDesc"));
				studentDetails.setFileUpdateTime(Utilities.convertStringToDate(rs.getString("fileUpdateTime")));
			} else {
				System.out.println("rs is null");
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return studentDetails;
	}

	private boolean postStudentDetails(StudentDetails student) {
		boolean result = false;
		Properties properties = new Utilities().getProperties();

		try {
			PreparedStatement prepareStatement = connection.prepareStatement("insert into \"StudentDetailsTable\" (\n"
					+ "	id, \"firstName\", \"lastName\", username, \"dateOfBirth\", gender, about, programs, name, \"fileUploadTime\", \"fileDesc\", \"fileUpdateTime\") values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			System.out.println("student req " + student);
			student.setId(Integer.parseInt(RandomStringUtils.randomNumeric(9)));
			prepareStatement.setInt(1, student.getId());
			prepareStatement.setString(2, student.getFirstName());
			prepareStatement.setString(3, student.getLastName());
			prepareStatement.setString(4, student.getUsername());
			prepareStatement.setString(5, Utilities.convertDateToString(student.getDateOfBirth()));
			prepareStatement.setString(6, student.getGender());
			prepareStatement.setString(7, student.getAbout());
			prepareStatement.setString(8, student.getPrograms());
			prepareStatement.setString(9, properties.getProperty("CLOUDFRONT_DOMAIN") + "/" + student.getUsername()
					+ "/" + student.getName());

			prepareStatement.setString(10, Utilities.convertDateToString(new Date(System.currentTimeMillis())));
			prepareStatement.setString(11, student.getFileDesc());
			prepareStatement.setString(12, "");

			int executeUpdate = prepareStatement.executeUpdate();
			System.out.println("executeUpdate " + executeUpdate);
			if (executeUpdate > 0)
				result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
}
