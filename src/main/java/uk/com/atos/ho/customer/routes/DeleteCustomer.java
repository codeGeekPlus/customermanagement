package uk.com.atos.ho.customer.routes;

import org.apache.camel.Predicate;
import org.apache.camel.builder.PredicateBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import uk.com.atos.ho.customer.exception.PrepareErrorResponse;
import uk.com.atos.ho.customer.model.HeaderReader;
import uk.com.atos.ho.customer.service.DeleteCustomerServiceImpl;

@Component
@Slf4j
public class DeleteCustomer extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		Predicate idNotNull = header("id").isNotNull();
		Predicate idNotEmpty = header("id").isNotEqualTo("");
		Predicate idValidation = PredicateBuilder.and(idNotNull, idNotEmpty);
		
		from("{{delete.customers.route}}")
		.setHeader("customer", body())
		.bean(HeaderReader.class)
		.to("bean-validator:headerValidator").id("deleteHeaderId") // validating headers using Camel validation
		.setBody(header("viewCustomers"))
		.log("Message Body : ${body} and Message Headers ${headers}")
		.choice()
			.when(idValidation)
				//.to("{{delete.customers.service}}?method=viewCustomers()").id("deleteCustomerId") // Calling deleteCustomerService
			   .bean(DeleteCustomerServiceImpl.class)
			.otherwise().throwException(new IllegalArgumentException()).bean(PrepareErrorResponse.class)
			.log("${header.id}");
	}

}

