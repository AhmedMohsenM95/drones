package com.musala.drones.model;

import com.musala.drones.model.enums.DroneModel;
import com.musala.drones.model.enums.DroneState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DroneDTO {

    @Length(min = 1, max = 100, message = "{required.field}")
    private String serialNumber;
    private DroneModel model;
    @Max(value = 500, message = "{drone.weight.limit}")
    private int weightLimit;
    @Max(value = 100, message = "{drone.percentage.limit}")
    private int batteryCapacity;
    private DroneState droneState;



}
