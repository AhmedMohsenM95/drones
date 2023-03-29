package com.musala.drones.service.framework;

import com.musala.drones.model.DroneDTO;
import com.musala.drones.model.MedicationDTO;
import com.musala.drones.model.entity.Drone;
import com.musala.drones.model.entity.Medication;

import java.util.List;

public interface DroneService {

    Drone registerDrone(DroneDTO droneDTO);
    void loadDrone(List<MedicationDTO> medicationDTOList, String droneId);
    List<Medication> retrieveDroneMedicationList(String droneId);
    List<Drone> retrieveAvailableDronesForLoading();
    int retrieveDroneBatteryLevel(String droneId);
}
