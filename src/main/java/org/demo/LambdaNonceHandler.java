package org.demo;

import java.util.HashMap;
import java.util.Map;

import org.demo.config.BrainSandboxConfig;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LambdaNonceHandler implements RequestHandler<LambdaRequest, LambdaResponse> {
	
	final ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public LambdaResponse handleRequest(LambdaRequest request, Context arg1) {
		
		String json = request.getBody();
		LambdaResponse response = new LambdaResponse();
		response.setIsBase64Encoded(false);
		response.setStatusCode(200);
		Map<String, String> headers = new HashMap<>();
		headers.put("Content-Type", "application/json");
		
		try {
			NonceRequest nonce = mapper.readValue(json, NonceRequest.class);
			TransactionRequest transRequest = new TransactionRequest()
				    .amount(nonce.getAmount())
				    .paymentMethodNonce(nonce.getNonce())
				    .options()
				    .submitForSettlement(true)
				    .done();
			Result<Transaction> transResult = BrainSandboxConfig.getGateway().transaction().sale(transRequest);
			String result = mapper.writeValueAsString(transResult);
			response.setBody(result);
		} catch (Exception e) {
			response.setStatusCode(400);
		}
		
		return response;
	}

}
