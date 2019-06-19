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

@RunWith(SpringJUnit4ClassRunner.class)
public class DeleteCustomerServiceImplTest {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private DeleteCustomerService deleteCustomerService = new DeleteCustomerServiceImpl();

	@Test
	public void givenMockingIsDoneByMockito_whenPostisCalled_souldReturnACustomerConfirmationString() throws Exception {
		String id = "1234";
		Mockito.when(restTemplate.getForObject(Matchers.anyString(), Matchers.eq(String.class))).thenReturn(id);
		String actualMsg = deleteCustomerService.deleteCustomer(id);
		String expectedMsg = "Customer Deleted successfully, Http Status :200 OK";
		Assert.assertEquals(expectedMsg, actualMsg);

	}

}
