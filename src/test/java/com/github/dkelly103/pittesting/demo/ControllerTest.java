package com.github.dkelly103.pittesting.demo;

import com.github.dkelly103.pittesting.demo.controller.SampleController;
import com.github.dkelly103.pittesting.demo.database.SampleRepository;
import com.github.dkelly103.pittesting.demo.model.SampleDataObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class ControllerTest {

	@MockBean
	SampleRepository sampleRepository;

	@MockBean
	RestTemplate restTemplate;

	@Autowired
	SampleController sampleController;

	SampleDataObject sampleDataObject = sampleDataObject();

	@BeforeEach
	public void setUp(){
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void sampleTest_goodDbResponse() {

		mockDatabaseResponse();

		try {
			sampleController.findAndPutByEmail("test@email.com");

			ArgumentCaptor<HttpEntity> httpEntityArgumentCaptor = ArgumentCaptor.forClass(HttpEntity.class);

			Mockito.verify(restTemplate, Mockito.times(1)).postForObject(
					Mockito.eq("http://localhost:3000/endpoint"),
					httpEntityArgumentCaptor.capture(),
					Mockito.eq(String.class)
			);

			HttpEntity result = httpEntityArgumentCaptor.getValue();

			Assertions.assertTrue(result.getHeaders().getAccept().contains(MediaType.APPLICATION_JSON));

		} catch (Exception e) {
			fail(e);
		}
	}

	SampleDataObject sampleDataObject(){
		SampleDataObject sampleDataObject = new SampleDataObject();
		sampleDataObject.setEmailAddress("test@email.com");
		sampleDataObject.setAge(30);
		return sampleDataObject;
	}

	void mockDatabaseResponse(){
		Mockito.doReturn(Optional.of(sampleDataObject)).when(sampleRepository).findByEmailAddress(any());
	}

}