//package com.example.demo.service;
//
//import com.example.demo.database.SampleRepository;
//import com.example.demo.exceptions.ResourceNotFoundException;
//import com.example.demo.model.SampleDataObject;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//public class BadSampleService {
//
//    @Autowired
//    private SampleRepository sampleRepository;
//
//    public void findByEmailAndPost(String emailAddress) {
//
//        Optional<SampleDataObject> sampleDataObjectOptional = sampleRepository.findByEmailAddress(emailAddress);
//
//        if (sampleDataObjectOptional.isEmpty()) {
//            throw new ResourceNotFoundException();
//        }
//
//        SampleDataObject sampleDataObject = sampleDataObjectOptional.get();
//
//        HttpEntity<SampleDataObject> httpEntity = new HttpEntity<>(sampleDataObject, getHttpHeaders());
//
//        RestTemplate restTemplate = new RestTemplate();
//
//        restTemplate.postForObject("http://localhost:3000/endpoint", httpEntity, String.class);
//    }
//
//    private HttpHeaders getHttpHeaders(){
//        HttpHeaders headers = new HttpHeaders();
//
//        List<MediaType> mediaTypes = new ArrayList<>();
//        mediaTypes.add(MediaType.APPLICATION_JSON);
//
//        headers.setAccept(mediaTypes);
//
//        return headers;
//    }
//
//}
