package org.demo;

import java.math.BigDecimal;

public class NonceRequest {
	BigDecimal amount;
	String nonce;
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	
}
