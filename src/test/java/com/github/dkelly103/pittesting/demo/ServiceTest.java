package com.github.dkelly103.pittesting.demo;

import com.github.dkelly103.pittesting.demo.database.SampleRepository;
import com.github.dkelly103.pittesting.demo.service.SampleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;

public class ServiceTest {

    @Mock
    RestTemplate restTemplate;

    @Mock
    SampleRepository sampleRepository;

    SampleService service = new SampleService(restTemplate, sampleRepository);

    @Test
    public void addTest_notNull(){
        int a = 10;
        int b = 11;
        int result = service.add(a, b);

        Assertions.assertNotEquals(result, 0);
    }

    @Test
    public void addTest_actualAnswer(){
        int a = 10;
        int b = 11;
        int result = service.add(a, b);

        Assertions.assertEquals(result, 21);
    }

}