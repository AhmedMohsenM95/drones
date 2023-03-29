package com.musala.drones.service.impl;

import com.musala.drones.exception.InvalidRequestException;
import com.musala.drones.mapper.DroneMapper;
import com.musala.drones.mapper.MedicationMapper;
import com.musala.drones.model.DroneDTO;
import com.musala.drones.model.MedicationDTO;
import com.musala.drones.model.entity.Drone;
import com.musala.drones.model.entity.Medication;
import com.musala.drones.repository.DroneRepository;
import com.musala.drones.repository.MedicationRepository;
import com.musala.drones.service.framework.DroneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class DroneServiceImpl implements DroneService {

    private final DroneRepository droneRepository;
    private final DroneMapper droneMapper;
    private final MedicationMapper medicationMapper;
    private final MedicationRepository medicationRepository;

    @Override
    public Drone registerDrone(DroneDTO droneDTO) {
        log.info("Registering drone...");
        if (droneRepository.existsById(droneDTO.getSerialNumber())){
            throw new InvalidRequestException("Drone with the same serialNumber is already registered.");
        }
        return droneRepository.save(droneMapper.fromDTO(droneDTO));
    }

    @Override
    public void loadDrone(List<MedicationDTO> medicationDTOList, String droneId) {
        Optional<Drone> optionalDrone = droneRepository.findById(droneId);
        if (!optionalDrone.isPresent()){
            throw new InvalidRequestException("Drone not found");
        }

        Drone drone = optionalDrone.get();
        if (drone.getBatteryCapacity() < 25){
            throw new InvalidRequestException("This drone cannot be loaded due to low battery level (Below 25%)");
        }

        int currentWeight = drone.getMedications().stream().mapToInt(Medication::getWeight).sum();
        long maxSize = 0;
        List<Medication> medications = new ArrayList<>();
        for (MedicationDTO medicationDTO : medicationDTOList) {
            currentWeight += medicationDTO.getWeight();
            maxSize += medicationDTO.getImage().getData().length;
            Medication medication = medicationMapper.fromDTO(medicationDTO);
            medication.setDrone(drone);
            medications.add(medication);
        }
        drone.setMedications(medications);
        //Check for the drone weight limit
        if (currentWeight > drone.getWeightLimit()){
            throw new InvalidRequestException("Loading failed, cannot load the drone with more than the maximum allowed weight.");
        }
        //10485760 bytes is 10MB
        if (maxSize > 10485760){
            throw new InvalidRequestException("Request is exceeding maximum allowed size.");
        }
        medicationRepository.saveAll(medications);
    }

    @Override
    public List<Medication> retrieveDroneMedicationList(String droneId) {
        return medicationRepository.findAllByDrone_SerialNumber(droneId);
    }

    @Override
    public List<Drone> retrieveAvailableDronesForLoading() {
        List<Drone> availableDrones = droneRepository.findAllByBatteryCapacityIsGreaterThan(24);
        Iterator<Drone> droneIterator = availableDrones.listIterator();
        Drone drone;
        //Removing already full drones which cannot take anymore weight.
        while (droneIterator.hasNext()){
            drone = droneIterator.next();
            int currentWeight = drone.getMedications().stream().mapToInt(Medication::getWeight).sum();
            if (currentWeight >= drone.getWeightLimit()){
                droneIterator.remove();
            }
        }
        return availableDrones;
    }

    @Override
    public int retrieveDroneBatteryLevel(String droneId) {
        Optional<Drone> optionalDrone = droneRepository.findById(droneId);
        if (!optionalDrone.isPresent()){
            throw new InvalidRequestException("Drone not found");
        }
        return optionalDrone.get().getBatteryCapacity();
    }
}
