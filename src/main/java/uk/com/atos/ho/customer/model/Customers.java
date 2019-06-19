package uk.com.atos.ho.customer.model;

import java.util.ArrayList;
import java.util.List;

public class Customers {

	List<Customer> customerList = new ArrayList<Customer>();

	public List<Customer> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}

}
