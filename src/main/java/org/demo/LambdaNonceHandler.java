package org.demo;

import java.util.HashMap;
import java.util.Map;

import org.demo.config.BrainSandboxConfig;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class LambdaNonceHandler implements RequestHandler<NonceRequest, NonceResponse> {
	
	@Override
	public NonceResponse handleRequest(NonceRequest nonce, Context arg1) {
		TransactionRequest request = new TransactionRequest()
			    .amount(nonce.getAmount())
			    .paymentMethodNonce(nonce.getNonce())
			    .options()
			      .submitForSettlement(true)
			      .done();
		
		Result<Transaction> result = BrainSandboxConfig.getGateway().transaction().sale(request);
		NonceResponse response = new NonceResponse();
		response.setIsBase64Encoded(false);
		response.setStatusCode(200);
		Map<String, String> headers = new HashMap<>();
		headers.put("content-type", "applicaton/json");
		ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(result);
            response.setBody(json);
        } catch (JsonProcessingException e) {
            response.setStatusCode(500);
        }
		
		return response;
	}

}
