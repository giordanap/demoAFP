package com.examen.demoAFP.Controller;

import com.examen.demoAFP.model.entity.Employee;
import com.examen.demoAFP.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Employee")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployees(){
        LOGGER.info("Made the listing request");
        return employeeRepository.findAll();
    }

    @PostMapping("/newEmployee")
    @ResponseStatus(HttpStatus.OK)
    public void createEmployee(@RequestBody Employee employee ){
        LOGGER.info("Made the request again");
        employeeRepository.save(employee);
    }

    @GetMapping("/oneEmployee/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable(value = "id") String id){
        LOGGER.info("Made the id request");
        Optional<Employee> employee = employeeRepository.findById(id);

        if(employee.isPresent()){
            return new ResponseEntity<>(employee.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(employee.get(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{id}")
    public String deleteEmplpyee(@PathVariable(value = "id") String id) {
        LOGGER.info("Made the delete request by Id");
        employeeRepository.deleteById(id);
        return "Employee deleted";
    }

    @PutMapping("/update/{id}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable(value = "id") String id){
        LOGGER.info("Made the update request by Id");
        employee.setId(id);
        employeeRepository.save(employee);
        return employee;
    }

}
