package org.demo.config;

import com.braintreegateway.BraintreeGateway;
import com.braintreegateway.Environment;

public class BrainSandboxConfig {
	
	private BrainSandboxConfig() {
		throw new IllegalStateException("Utility class");
	}
	
	private static BraintreeGateway gateway = new BraintreeGateway(
			  Environment.SANDBOX,
			  System.getenv("MERCHANT_ID"),
			  System.getenv("PUBLIC_KEY"),
			  System.getenv("PRIVATE_KEY")
			);
	
	public static BraintreeGateway getGateway() {
		return gateway;
	}
}
