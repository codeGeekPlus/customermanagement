package uk.com.atos.ho.customer.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service("{{delete.customers.service}}")
public class DeleteCustomerServiceImpl implements DeleteCustomerService {

	@Override
	public String deleteCustomer(String id) {

		return "Customer Deleted successfully, Http Status :" + HttpStatus.OK;
	}

}

