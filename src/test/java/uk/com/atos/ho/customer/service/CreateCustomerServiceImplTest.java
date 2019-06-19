package uk.com.atos.ho.customer.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import junit.framework.Assert;
import uk.com.atos.ho.customer.model.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
public class CreateCustomerServiceImplTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private CreateCustomerService createCustomerService = new CreateCustomerServiceImpl();

	@Test
	public void givenMockingIsDoneByMockito_whenPostisCalled_souldReturnACustomerConfirmationString() throws Exception {
		Customer customer = new SampleCustomer().getCustomer();
		Mockito.when(restTemplate.getForObject(Matchers.anyString(), Matchers.eq(Customer.class))).thenReturn(customer);
		String actualMsg = createCustomerService.createCustomer(customer);
		String expectedMsg = "Customer created successfully, Http Status :200 OK";
		Assert.assertEquals(expectedMsg, actualMsg);

	}
}
