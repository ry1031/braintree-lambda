package org.demo;

import org.demo.config.BrainSandboxConfig;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.braintreegateway.Result;
import com.braintreegateway.Transaction;
import com.braintreegateway.TransactionRequest;

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
		response.setResult(result);

		return response;
	}

}
