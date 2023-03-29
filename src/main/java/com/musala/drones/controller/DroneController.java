package com.musala.drones.controller;

import com.musala.drones.exception.InvalidRequestException;
import com.musala.drones.model.DroneDTO;
import com.musala.drones.model.entity.Drone;
import com.musala.drones.model.enums.DroneState;
import com.musala.drones.service.framework.DroneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/drone")
public class DroneController {

    private final DroneService droneService;
    @PostMapping
    public ResponseEntity<Drone> registerDrone(@Valid @RequestBody DroneDTO droneDTO){
        if (droneDTO.getBatteryCapacity() < 25 && droneDTO.getDroneState().equals(DroneState.LOADING)){
            throw new InvalidRequestException("Drone cannot be in loading state if the battery percentage is below 25%");
        }
        return ResponseEntity.ok(droneService.registerDrone(droneDTO));
    }

}
