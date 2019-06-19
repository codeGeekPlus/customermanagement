package uk.com.atos.ho.customer.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import uk.com.atos.ho.customer.model.Customer;

@Component
@Slf4j
public class CustomerEndpoints extends RouteBuilder {

	@Override
	public void configure() throws Exception {

		restConfiguration()
		    .bindingMode(RestBindingMode.auto)
		    .apiContextPath("/doc")
			.apiProperty("api.title", "{{api.title}}")
			.apiProperty("api.version", "{{api.version}}")
			.component("servlet");
		
		rest("{{create.customer.endpoint}}")
			.consumes("{{content.type}}")
			.produces("{{content.type}}")
			.description("{{create.customer.desc}}")
			.post()
			.type(Customer.class)
			.route()
			.to("{{create.customer.route}}").id("createCustomerEndpointId");
		
		rest("{{get.customers.endpoint}}")
			.consumes("{{content.type}}")
			.produces("{{content.type}}")
			.description("{{get.customers.desc}}")
			.get()
			.to("{{get.customers.route}}").id("getCustomersId");
		
		rest("{{delete.customers.endpoint}}")
			.consumes("{{content.type}}")
			.produces("{{content.type}}")
			.description("{{delete.customer.desc}}")
			.delete()
			.param().name("{{delete.customer.id}}").type(RestParamType.query).required(true).endParam()
			.route()
			.to("{{delete.customers.route}}").id("deleteCustomerId");
	}

}
