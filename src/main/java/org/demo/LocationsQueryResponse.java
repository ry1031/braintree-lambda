package org.demo;

import java.util.Map;

public class LocationsQueryResponse {
	Boolean isBase64Encoded;
	Integer statusCode;
	Map<String, String> headers;
	String body;
	
	public Boolean getIsBase64Encoded() {
		return isBase64Encoded;
	}
	public void setIsBase64Encoded(Boolean isBase64Encoded) {
		this.isBase64Encoded = isBase64Encoded;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public Map<String, String> getHeaders() {
		return headers;
	}
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
}
