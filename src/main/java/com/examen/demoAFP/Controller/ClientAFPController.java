package com.examen.demoAFP.Controller;

import com.examen.demoAFP.model.entity.ClientAFP;
import com.examen.demoAFP.model.entity.Employee;
import com.examen.demoAFP.repository.ClientAFPRepository;
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
@RequestMapping("/api/ClientAFP")
@Slf4j
public class ClientAFPController {

    @Autowired
    private ClientAFPRepository clientAFPRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientAFP> getAllClientAFP(){
        LOGGER.info("Made the listing request");
        return clientAFPRepository.findAll();
    }

    @PostMapping("/newClientAFP")
    @ResponseStatus(HttpStatus.OK)
    public String createClientAFP(@RequestBody ClientAFP clientAFP ){
        LOGGER.info("Made the request again");
        var oldClientAFP = clientAFPRepository.findByDni(clientAFP.getDni());

        if(oldClientAFP != null){
            return "Client already exist";
        } else {
            clientAFPRepository.save(clientAFP);
            return "Client AFP created";
        }

    }

    @GetMapping("/oneClientAFP/{id}")
    public ResponseEntity<ClientAFP> getClientAFP(@PathVariable(value = "id") String id){
        LOGGER.info("Made the id request");
        Optional<ClientAFP> clientAFP = clientAFPRepository.findById(id);

        if(clientAFP.isPresent()){
            return new ResponseEntity<>(clientAFP.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(clientAFP.get(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("delete/{id}")
    public String deleteClientAFP(@PathVariable(value = "id") String id) {
        LOGGER.info("Made the delete request by Id");
        clientAFPRepository.deleteById(id);
        return "Client AFP deleted";
    }

    @PutMapping("/update/{id}")
    public ClientAFP updateClientAFP(@RequestBody ClientAFP clientAFP, @PathVariable(value = "id") String id){
        LOGGER.info("Made the update request by Id");
        clientAFP.setId(id);
        clientAFPRepository.save(clientAFP);
        return clientAFP;
    }

}
