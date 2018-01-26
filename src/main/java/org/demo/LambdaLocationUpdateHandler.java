package org.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class LambdaLocationUpdateHandler implements RequestHandler<LocationsUpdateRequest, LambdaResponse> {
	
	static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().build();
    static DynamoDB dynamoDB = new DynamoDB(client);
    
    static String tableName = "Appointment";

	@Override
	public LambdaResponse handleRequest(LocationsUpdateRequest input, Context arg1) {
		LambdaResponse response = new LambdaResponse();
		response.setIsBase64Encoded(false);
		response.setStatusCode(200);
		Map<String, String> headers = new HashMap<>();
		headers.put("content-type", "applicaton/json");
		
		Table table = dynamoDB.getTable(tableName);
        try {
        	
        	ObjectMapper objectMapper = new ObjectMapper();
        	TypeFactory typeFactory = objectMapper.getTypeFactory();
        	List<String> locationList = objectMapper.readValue(input.getLocations(), typeFactory.constructCollectionType(List.class, String.class));
        	
        	UpdateItemSpec updateItemSpec = new UpdateItemSpec().withPrimaryKey("Id", input.getId())
                    .withUpdateExpression("remove Locations");

            table.updateItem(updateItemSpec);
            
            updateItemSpec = new UpdateItemSpec().withPrimaryKey("Id", input.getId())
            		.withUpdateExpression("set Locations = :locations")
                    .withValueMap(new ValueMap().withList(":locations", locationList));

            table.updateItem(updateItemSpec);
            
        	response.setBody("success");

        }
        catch (Exception e) {
        	response.setStatusCode(400);
        }
		
		return response;
	}
}
