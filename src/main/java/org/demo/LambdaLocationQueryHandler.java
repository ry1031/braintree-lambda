package org.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LambdaLocationQueryHandler implements RequestHandler<LocationsQueryRequest, LocationsQueryResponse> {
	
	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    static DynamoDB dynamoDB = new DynamoDB(client);
    
    static String tableName = "Appointment";

	@Override
	public LocationsQueryResponse handleRequest(LocationsQueryRequest input, Context arg1) {
		LocationsQueryResponse response = new LocationsQueryResponse();
		response.setIsBase64Encoded(false);
		response.setStatusCode(200);
		Map<String, String> headers = new HashMap<>();
		headers.put("content-type", "applicaton/json");
		
		Table table = dynamoDB.getTable(tableName);
        try {

        	Item item = table.getItem("Id", input.getUserId());
        	
        	@SuppressWarnings("unchecked")
			List<String> locations = (List<String>) item.get("Locations");
        	
        	ObjectMapper mapper = new ObjectMapper();
        	String ret = mapper.writeValueAsString(locations);
        	response.setBody(ret);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
		
		return response;
	}
}
