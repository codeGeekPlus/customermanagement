package uk.com.atos.ho.customer.service;

import uk.com.atos.ho.customer.model.Customer;

public class SampleCustomer {

	public Customer getCustomer() {
		Customer customer = new Customer();
		customer.setId("1234");
		customer.setFirstName("firstName");
		customer.setSurname("surname");
		return customer;
	}

}
