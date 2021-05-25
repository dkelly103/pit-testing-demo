package com.github.dkelly103.pittesting.demo.service;

import com.github.dkelly103.pittesting.demo.database.SampleRepository;
import com.github.dkelly103.pittesting.demo.exceptions.ResourceNotFoundException;
import com.github.dkelly103.pittesting.demo.model.SampleDataObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class SampleService {

    private final RestTemplate restTemplate;
    private final SampleRepository sampleRepository;

    @Autowired
    public SampleService(RestTemplate restTemplate, SampleRepository sampleRepository){
        this.restTemplate = restTemplate;
        this.sampleRepository = sampleRepository;
    }

    public void findByEmailAndPost(String emailAddress) {

        log.info("{}: Request to find email {} and post", this.getClass(), emailAddress);

        Optional<SampleDataObject> sampleDataObjectOptional = sampleRepository.findByEmailAddress(emailAddress);

        if (sampleDataObjectOptional.isEmpty()) {
            throw new ResourceNotFoundException();
        }

        SampleDataObject sampleDataObject = sampleDataObjectOptional.get();

        log.info("{}: Found sample object: {}", this.getClass(), sampleDataObject);

        HttpHeaders headers = getHttpHeaders();

        HttpEntity<SampleDataObject> httpEntity = new HttpEntity<>(sampleDataObject, headers);

        restTemplate.postForObject("http://localhost:3000/endpoint", httpEntity, String.class);
    }

    private HttpHeaders getHttpHeaders(){
        HttpHeaders headers = new HttpHeaders();

        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);

        headers.setAccept(mediaTypes);

        return headers;
    }

    public int add(int a, int b){
        return a + b;
    }

}
