package com.examen.demoAFP.repository;

import com.examen.demoAFP.model.entity.ClientAFP;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientAFPRepository extends MongoRepository<ClientAFP, String> {
    public ClientAFP findByDni(String dni);
}
