package uk.com.atos.ho.customer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import uk.com.atos.ho.customer.model.Customer;
import uk.com.atos.ho.customer.model.Customers;

@Service("{{get.customers.service}}")
public class GetAllCustomersServiceImpl implements GetAllCustomersService {

	@Override
	public Customers getAllCustomers() {

		Customers customers = new Customers();
		List<Customer> customerList = new ArrayList<Customer>();
		Customer customer = new Customer();
		customer.setFirstName("firstName");
		customer.setId("1234");
		customer.setSurname("surname");
		customerList.add(customer);
		customers.setCustomerList(customerList);
		return customers;

	}

}
