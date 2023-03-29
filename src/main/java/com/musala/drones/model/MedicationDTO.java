package com.musala.drones.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicationDTO {

    @Length(min = 1, max = 255, message = "{required.field}")
    //This pattern matches only letters, numbers, ‘-‘, ‘_’
    @Pattern(regexp = "^[a-zA-Z0-9-_]+$",message = "{medication.name.pattern}")
    private String name;
    private int weight;
    @Pattern(regexp = "^[A-Z0-9_]+$",message = "{medication.code.pattern}")
    private String code;
    private FileDTO image;



}
