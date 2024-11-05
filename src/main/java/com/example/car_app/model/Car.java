
package com.example.car_app.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Car {

    @Id
    private String id; // Change the type to String
    
    private String make;
    private String model;
    private String colour;
    private String createdAt;
    private String createdMessage;
    private String updatedAt;
    private String updatedMessage;
}

// UPDATE