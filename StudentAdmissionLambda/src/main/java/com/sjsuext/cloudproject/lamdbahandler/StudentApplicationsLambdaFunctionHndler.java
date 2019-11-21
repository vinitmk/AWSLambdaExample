package com.sjsuext.cloudproject.lamdbahandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.sjsuext.cloudproject.model.StudentAPIRequest;
import com.sjsuext.cloudproject.model.StudentDetails;
import com.sjsuext.cloudproject.util.DatabaseConnection;
import com.sjsuext.cloudproject.util.Utilities;

public class StudentApplicationsLambdaFunctionHndler
		implements RequestHandler<StudentAPIRequest, List<StudentDetails>> {

	@Override
	public List<StudentDetails> handleRequest(StudentAPIRequest input, Context context) {

		StudentDetails studentDetails = null;
		Connection connection = DatabaseConnection.getRemoteConnection();
		List<StudentDetails> list = null;

		if (input.getHttpMethod() != null) {
			try {
				PreparedStatement prepareStatement = connection
						.prepareStatement("select distinct username from \"StudentDetailsTable\"");
				ResultSet rs = prepareStatement.executeQuery();
				list = new ArrayList<StudentDetails>();
				while (rs.next()) {

					PreparedStatement ps = connection
							.prepareStatement("select * from \"StudentDetailsTable\" where username = ?");
					ps.setString(1, rs.getString("username"));
					ResultSet result = ps.executeQuery();
					if (result.next()) {
						studentDetails = new StudentDetails();
						studentDetails.setId(Integer.parseInt(result.getString("id")));
						studentDetails.setFirstName(result.getString("firstName"));
						studentDetails.setLastName(result.getString("lastName"));
						studentDetails.setPrograms(result.getString("programs"));
						studentDetails.setName(result.getString("name"));
						studentDetails.setFileDesc(result.getString("fileDesc"));
						studentDetails
								.setFileUploadTime(Utilities.convertStringToDate(result.getString("fileUploadTime")));
						studentDetails
								.setFileUpdateTime(Utilities.convertStringToDate(result.getString("fileUpdateTime")));
					}
					list.add(studentDetails);
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}
}
