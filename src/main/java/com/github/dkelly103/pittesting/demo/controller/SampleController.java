package com.github.dkelly103.pittesting.demo.controller;

import com.github.dkelly103.pittesting.demo.service.SampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SampleController {

    private SampleService sampleService;

    @Autowired
    public SampleController(SampleService sampleService){
        this.sampleService = sampleService;
    }

    @PostMapping("/findAndPutByEmail")
    public void findAndPutByEmail(String emailAddress) {

        log.info("{}: Request to find email {} and post", this.getClass(), emailAddress);

        sampleService.findByEmailAndPost(emailAddress);
    }
}