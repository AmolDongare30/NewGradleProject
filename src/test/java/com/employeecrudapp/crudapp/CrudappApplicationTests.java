package com.employeecrudapp.crudapp;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class CrudappApplicationTests {

	@Test
	void contextLoads() 
	{
		
	}
	// Test API with junit and rest template
	
	@Test
	public void testgetAllEmployees() throws URISyntaxException
	{
		System.out.println("Test started");
		RestTemplate restTemplate=new RestTemplate();
		String url="http://localhost:8080/getAllEmps";
		URI uri=new URI(url);
		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
		Assertions.assertEquals(200, response.getStatusCodeValue());
		System.out.println("Test Ended");
	}

}
