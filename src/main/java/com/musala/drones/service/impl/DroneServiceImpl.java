package com.musala.drones.service.impl;

import com.musala.drones.mapper.DroneMapper;
import com.musala.drones.model.DroneDTO;
import com.musala.drones.model.entity.Drone;
import com.musala.drones.repository.DroneRepository;
import com.musala.drones.service.framework.DroneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;
    private final DroneMapper droneMapper;

    @Override
    public Drone registerDrone(DroneDTO droneDTO) {
        log.info("Registering drone...");
        return droneRepository.save(droneMapper.fromDTO(droneDTO));
    }
}
