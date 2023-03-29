package com.musala.drones.mapper;

import com.musala.drones.model.DroneDTO;
import com.musala.drones.model.entity.Drone;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DroneMapper {

    Drone fromDTO(DroneDTO droneDTO);
}
