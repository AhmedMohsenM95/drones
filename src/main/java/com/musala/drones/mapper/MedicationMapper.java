package com.musala.drones.mapper;

import com.musala.drones.model.DroneDTO;
import com.musala.drones.model.MedicationDTO;
import com.musala.drones.model.entity.Drone;
import com.musala.drones.model.entity.Medication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MedicationMapper {

    @Mapping(target = "image", source = "image.data")
    @Mapping(target = "imageName", source = "image.fileName")
    @Mapping(target = "imageExtension", source = "image.fileExtension")
    Medication fromDTO(MedicationDTO medicationDTO);
}
