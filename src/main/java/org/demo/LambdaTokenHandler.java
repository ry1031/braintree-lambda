package org.demo;

import org.demo.config.BrainSandboxConfig;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaTokenHandler implements RequestHandler<TokenRequest, TokenResponse> {
	
	@Override
	public TokenResponse handleRequest(TokenRequest input, Context arg1) {
		TokenResponse response = new TokenResponse();
		response.setToken(BrainSandboxConfig.getGateway().clientToken().generate());
		return response;
	}

}
