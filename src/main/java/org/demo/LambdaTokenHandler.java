package org.demo;

import java.util.HashMap;
import java.util.Map;

import org.demo.config.BrainSandboxConfig;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaTokenHandler implements RequestHandler<TokenRequest, TokenResponse> {
	
	@Override
	public TokenResponse handleRequest(TokenRequest input, Context arg1) {
		TokenResponse response = new TokenResponse();
		response.setIsBase64Encoded(false);
		response.setStatusCode(200);
		Map<String, String> headers = new HashMap<>();
		headers.put("content-type", "applicaton/json");
		
		response.setBody(BrainSandboxConfig.getGateway().clientToken().generate());
		
		return response;
	}

}
