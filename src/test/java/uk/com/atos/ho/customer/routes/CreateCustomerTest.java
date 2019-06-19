package uk.com.atos.ho.customer.routes;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.PropertyInject;
import org.apache.camel.builder.ExchangeBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;

public class CreateCustomerTest extends ManageCustomersRoutesTest {

	@Produce
	protected ProducerTemplate template;

	@PropertyInject("{{create.customer.route}}")
	String createCustomer;

	@Override
	public boolean isUseAdviceWith() {
		return true;
	}

	@EndpointInject(uri = "mock:result")
	MockEndpoint results;

	@Test
	public void shouldTestCreateCustomer() throws Exception {

		context.start();
		Exchange output = template.send(createCustomer, getExchange());
		String createOutput = output.getIn().getBody(String.class);
		assertEquals(createOutput, "Customer created successfully, Http Status :200 OK");
		context.stop();

	}

	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new CreateCustomer();
	}

	private Exchange getExchange() throws Exception {
		return ExchangeBuilder.anExchange(context).withBody(loadJsonPayloadFromClassPath("createCustomer.json"))
				.withHeader("requestId", "createCustomer").withHeader("userId", "testUser").build();
	}

}
