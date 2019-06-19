package uk.com.atos.ho.customer.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import uk.com.atos.ho.customer.model.Customer;

@Service("{{create.customer.service}}")
public class CreateCustomerServiceImpl implements CreateCustomerService {

	public String createCustomer(Customer customer) {
		return "Customer created successfully, Http Status :" + HttpStatus.OK;
	}

	

}
