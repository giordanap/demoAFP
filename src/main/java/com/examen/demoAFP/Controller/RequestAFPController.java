package com.examen.demoAFP.Controller;

import com.examen.demoAFP.model.entity.ClientAFP;
import com.examen.demoAFP.model.entity.RequestAFP;
import com.examen.demoAFP.repository.ClientAFPRepository;
import com.examen.demoAFP.repository.RequestAFPRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;
import java.util.List;

@RestController
@RequestMapping("/api/RequestAFP")
@Slf4j
public class RequestAFPController {

    @Autowired
    private RequestAFPRepository requestAFPRepository;
    private ClientAFPRepository clientAFPRepository;


    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<RequestAFP> getAllRequestAFP(){
        LOGGER.info("Made the listing request");
        return requestAFPRepository.findAll();
    }

    @PostMapping("/newRequestAFP")
    @ResponseStatus(HttpStatus.OK)
    public String createRequestAFP(@RequestBody RequestAFP requestAFP){
        LOGGER.info("Made the request again");
        var oldRequestAFP = requestAFPRepository.findByDni(requestAFP.getDni());

        if(oldRequestAFP != null){
            return "Request AFP already exist";
        } else {
            if(requestAFP.getWithDrawalAmount() > requestAFP.getAvailableAmount()){
                return "No se puede registrar la solicitud. Monto mayor que el permitido";
            } else {
                if (requestAFP.getWithDrawalAmount() < requestAFP.getAvailableAmount() * 0.5) {
                    return "Monto minimo no cubierto por favor revise el monto minimo a retirar";
                } else {
                    requestAFPRepository.save(requestAFP);
                    return "Request AFP created";
                }
            }
        }

    }
/*
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
*/
}
