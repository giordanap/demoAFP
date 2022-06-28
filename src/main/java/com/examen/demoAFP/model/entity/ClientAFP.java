package com.examen.demoAFP.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("clientAFP")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClientAFP {

    @Id
    private String id;
    private String idEmployee;
    private String dni;
    private String afp;
    private String idRequestAFP;

}
