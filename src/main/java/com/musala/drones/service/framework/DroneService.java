package com.musala.drones.service.framework;

import com.musala.drones.model.DroneDTO;
import com.musala.drones.model.entity.Drone;

public interface DroneService {

    Drone registerDrone(DroneDTO droneDTO);
}
