package com.musala.drones.controller;

import com.musala.drones.exception.InvalidRequestException;
import com.musala.drones.model.DroneDTO;
import com.musala.drones.model.MedicationDTO;
import com.musala.drones.model.entity.Drone;
import com.musala.drones.model.entity.Medication;
import com.musala.drones.model.enums.DroneState;
import com.musala.drones.service.framework.DroneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/drone")
@Validated
public class DroneController {

    private final DroneService droneService;

    @PostMapping
    public ResponseEntity<Drone> registerDrone(@Valid @RequestBody DroneDTO droneDTO) {
        if (droneDTO.getBatteryCapacity() < 25 && droneDTO.getDroneState().equals(DroneState.LOADING)) {
            throw new InvalidRequestException("Drone cannot be in loading state if the battery percentage is below 25%");
        }
        return ResponseEntity.ok(droneService.registerDrone(droneDTO));
    }

    @PostMapping("/{droneId}/load")
    public ResponseEntity<Drone> loadMedicationIntoDrone(@Valid @RequestBody List<MedicationDTO> medicationDTOList, @PathVariable("droneId") String droneId) {
        droneService.loadDrone(medicationDTOList, droneId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{droneId}/medications")
    public ResponseEntity<List<Medication>> retrieveDroneMedicationList(@PathVariable("droneId") String droneId){
        return ResponseEntity.ok(droneService.retrieveDroneMedicationList(droneId));
    }

    @GetMapping("/load/available")
    public ResponseEntity<List<Drone>> retrieveAvailableDronesForLoading(){
        return ResponseEntity.ok(droneService.retrieveAvailableDronesForLoading());
    }

    @GetMapping("/{droneId}/battery")
    public ResponseEntity<Integer> retrieveDroneBatteryLevel(@PathVariable("droneId") String droneId){
        return ResponseEntity.ok(droneService.retrieveDroneBatteryLevel(droneId));
    }

}
