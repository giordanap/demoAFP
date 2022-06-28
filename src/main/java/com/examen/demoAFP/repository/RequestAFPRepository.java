package com.examen.demoAFP.repository;

import com.examen.demoAFP.model.entity.RequestAFP;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RequestAFPRepository extends MongoRepository<RequestAFP, String> {
    public RequestAFP findByDni(String dni);
}
