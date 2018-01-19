package org.demo;

import com.braintreegateway.Result;
import com.braintreegateway.Transaction;

public class NonceResponse {
	Result<Transaction> result;

	public Result<Transaction> getResult() {
		return result;
	}

	public void setResult(Result<Transaction> result) {
		this.result = result;
	}
	
}
