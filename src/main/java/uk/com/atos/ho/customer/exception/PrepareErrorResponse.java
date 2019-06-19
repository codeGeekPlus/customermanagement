package uk.com.atos.ho.customer.exception;

import org.apache.camel.Exchange;
import org.apache.camel.Handler;
import org.apache.camel.Message;
import org.apache.camel.json.simple.JsonObject;
import org.springframework.http.MediaType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrepareErrorResponse {

	@Handler
	public void prepareErrorResponse(Exchange exchange) {

		Throwable cause = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);
		Message message = exchange.getOut();

		if (cause instanceof IllegalArgumentException)
			getErrorMessage(message);

	}

	private void getErrorMessage(Message message) {
		JsonObject errorMessage = new JsonObject();
		message.setHeader(Exchange.CONTENT_TYPE, MediaType.APPLICATION_JSON);
		message.setHeader(Exchange.HTTP_RESPONSE_CODE, 400);
		errorMessage.put("error", "Bad Request");
		errorMessage.put("message", CustomPayloadException.INCORRECT_REQUEST_PARAM);
		message.setBody(errorMessage);
	}

}
