package com.examen.demoAFP.repository;

import com.examen.demoAFP.model.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
    public Employee findByDni(String dni);
}
