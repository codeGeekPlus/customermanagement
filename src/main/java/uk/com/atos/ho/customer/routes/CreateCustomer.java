package uk.com.atos.ho.customer.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import uk.com.atos.ho.customer.model.Customer;
import uk.com.atos.ho.customer.model.HeaderReader;
import uk.com.atos.ho.customer.service.CreateCustomerServiceImpl;

@Component
@Slf4j
public class CreateCustomer extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		from("{{create.customer.route}}")
		        .log("Input Message Body : ${body} ")
				.setHeader("customer", body()).bean(HeaderReader.class)
				.to("bean-validator:headerValidator")
				.id("createHeaderId") // validating headers using Camel validation
				.setBody(header("customer"))
				.log("Message Body : ${body} and Message Headers ${headers}")
				.to("bean-validator:bodyValidator").id("validateBodyId")
				.process(new Processor() {
					
					@Override
					public void process(Exchange exchange) throws Exception {
						convertBodyToObject(exchange);
						
					}
				})
				.bean(CreateCustomerServiceImpl.class);
				

	}
	
	private void convertBodyToObject(Exchange ex) throws Exception{
		
		String json = ex.getIn().getBody(String.class);
		ex.getIn().setBody(new ObjectMapper().readValue(json, Customer.class));
		
	}

}

