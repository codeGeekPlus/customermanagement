package uk.com.atos.ho.customer.model;

import org.hibernate.validator.constraints.NotBlank;

public class RequestHeaders {

	@NotBlank
	String requestId;

	@NotBlank
	String userId;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
