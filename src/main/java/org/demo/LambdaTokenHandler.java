package org.demo;

import java.util.HashMap;
import java.util.Map;

import org.demo.config.BrainSandboxConfig;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaTokenHandler implements RequestHandler<LambdaRequest, LambdaResponse> {
	
	@Override
	public LambdaResponse handleRequest(LambdaRequest request, Context arg1) {
		
		LambdaResponse response = new LambdaResponse(); 
		response.setIsBase64Encoded(false);
		response.setStatusCode(200);
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		
		response.setBody(BrainSandboxConfig.getGateway().clientToken().generate());
		
		return response;
	}

}
