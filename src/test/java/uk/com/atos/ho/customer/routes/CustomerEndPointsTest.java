package uk.com.atos.ho.customer.routes;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.PropertyInject;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.ExchangeBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.junit.Test;

import uk.com.atos.ho.customer.model.Customer;
import uk.com.atos.ho.customer.model.Customers;


public class CustomerEndPointsTest extends ManageCustomersRoutesTest{

	@Produce
	protected ProducerTemplate template;


	@PropertyInject("{{create.customer.endpoint}}")
	String createCustomer;
	
	@PropertyInject("{{get.customers.endpoint}}")
	String getAllCustomers;
	
	@PropertyInject("{{delete.customers.endpoint}}")
	String deleteCustomer;

	@Override
	public boolean isUseAdviceWith() {
		return true;
	}

	@EndpointInject(uri = "mock:result")
	MockEndpoint results;

	

	@Test
	public void shouldTestCreateCustomerRestConfiguration() throws Exception {

		//String jsonFile = loadJsonPayloadFromClassPath("createCustomer.json");
		context.getRouteDefinitions().get(0).adviceWith(context, new AdviceWithRouteBuilder() {

			@Override
			public void configure() throws Exception {

				weaveById("createCustomerEndpointId").replace().to("mock:result").process(new Processor() {

					@Override
					public void process(Exchange exchange) throws Exception {
						exchange.getIn().setBody(getCreateCustomerResponse());

					}

				});
			}
		});
		context.start();
		results.expectedMessageCount(1);
		template.send(createCustomer, getExchange());
		context.stop();

	}
	
	@Test
	public void shouldTestGetAllCustomerRestConfiguration() throws Exception {

		//String jsonFile = loadJsonPayloadFromClassPath("createCustomer.json");
		context.getRouteDefinitions().get(0).adviceWith(context, new AdviceWithRouteBuilder() {

			@Override
			public void configure() throws Exception {

				weaveById("getCustomersId").replace().to("mock:result").process(new Processor() {

					@Override
					public void process(Exchange exchange) throws Exception {
						exchange.getIn().setBody(getCustomers());

					}

				});
			}
		});
		context.start();
		results.expectedMessageCount(1);
		template.send(getAllCustomers, getExchange());
		context.stop();

	}
	
	@Test
	public void shouldTestDeleteCustomerByIdRestConfiguration() throws Exception {

		context.getRouteDefinitions().get(0).adviceWith(context, new AdviceWithRouteBuilder() {

			@Override
			public void configure() throws Exception {

				weaveById("deleteCustomerId").replace().to("mock:result").process(new Processor() {

					@Override
					public void process(Exchange exchange) throws Exception {
						exchange.getIn().setBody("Customer Deleted successfully, Http Status :200 OK");

					}

				});
			}
		});
		context.start();
		results.expectedMessageCount(1);
		template.send(deleteCustomer, getExchange());
		context.stop();

	}
	
	
	@Override
	protected RouteBuilder createRouteBuilder() throws Exception {
		return new CustomerEndpoints();
	}

	private Exchange getExchange() throws Exception {
		return ExchangeBuilder.anExchange(context).withBody(loadJsonPayloadFromClassPath("createCustomer.json"))
				.withHeader("requestId", "createCustomer").withHeader("userId", "testUser").build();
	}

	private Customer getCreateCustomerResponse() {

		Customer customer = new Customer();
		customer.setId("1234");
		customer.setFirstName("firstName");
		customer.setSurname("surname");
		return customer;
	}
	
	private Customers getCustomers() {
		Customers customers = new Customers();
		List<Customer> customerList = new ArrayList();
		customerList.add(getCreateCustomerResponse());
		customers.setCustomerList(customerList);
		return customers;
	}

}
