package uk.com.atos.ho.customer.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import uk.com.atos.ho.customer.model.Customers;
import uk.com.atos.ho.customer.model.HeaderReader;
import uk.com.atos.ho.customer.service.GetAllCustomersServiceImpl;
@Component
@Slf4j
public class GetCustomers extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		from("{{get.customers.route}}")
			.setHeader("customer", body())
			.bean(HeaderReader.class)
			.to("bean-validator:headerValidator")
			.id("viewHeaderId") // validating headers using Camel validation
			.setBody(header("customer"))
			.log("Message Body : ${body} and Message Headers ${headers}")
		    .bean(GetAllCustomersServiceImpl.class)
		    .process(new Processor() {
				
				@Override
				public void process(Exchange exchange) throws Exception {
					convertBodyToJsonString(exchange);
				}
			});
	}

	private void convertBodyToJsonString(Exchange exchange) throws Exception {

		exchange.getIn().setBody(new ObjectMapper().writeValueAsString(exchange.getIn().getBody(Customers.class)));
	}
}

