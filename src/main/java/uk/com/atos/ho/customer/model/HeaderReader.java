package uk.com.atos.ho.customer.model;

import java.util.Map;

import org.apache.camel.Headers;

public class HeaderReader {

	public RequestHeaders buildHeaders(@Headers Map<String, Object> headers) {

		RequestHeaders requestHeaders = new RequestHeaders();
		requestHeaders.setRequestId((String) headers.get("requestId"));
		requestHeaders.setUserId((String) headers.get("userId"));
		return requestHeaders;

	}

}
