package com.musala.drones.repository;

import com.musala.drones.model.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, String> {

    List<Medication> findAllByDrone_SerialNumber(String serialNumber);
}
