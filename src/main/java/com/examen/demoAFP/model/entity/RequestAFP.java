package com.examen.demoAFP.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("requestAFP")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestAFP {

    @Id
    private String id;
    private String idEmployee;
    private String dni;
    private int availableAmount;
    private String withDrawalDate;
    private int withDrawalAmount;
    private String idClienteAFP;

}
