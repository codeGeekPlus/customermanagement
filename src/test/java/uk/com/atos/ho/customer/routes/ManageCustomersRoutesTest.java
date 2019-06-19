package uk.com.atos.ho.customer.routes;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.camel.CamelContext;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.test.junit4.CamelTestSupport;

public class ManageCustomersRoutesTest extends CamelTestSupport {

	@Override
	protected CamelContext createCamelContext() throws Exception {
		CamelContext camelContext = new DefaultCamelContext();
		PropertiesComponent propertiesComponent = camelContext.getComponent("properties", PropertiesComponent.class);
		propertiesComponent.setLocation("classpath:application.properties");
		return camelContext;
	}

	public String loadJsonPayloadFromClassPath(String payload) throws URISyntaxException, IOException {

		Path path = Paths.get(this.getClass().getClassLoader().getResource(payload).toURI());
		byte[] fileBytes = Files.readAllBytes(path);
		return new String(fileBytes);
	}

}
