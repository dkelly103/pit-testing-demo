package com.github.dkelly103.pittesting.demo.database;

import java.util.List;
import java.util.Optional;

import com.github.dkelly103.pittesting.demo.model.SampleDataObject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleRepository extends MongoRepository<SampleDataObject, String> {

    Optional<SampleDataObject> findByEmailAddress(String emailAddress);
    List<SampleDataObject> findByAge(int age);

}