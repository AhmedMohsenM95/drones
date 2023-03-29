package com.musala.drones.repository;

import com.musala.drones.model.entity.DroneBatteryAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneBatteryAuditRepository extends JpaRepository<DroneBatteryAudit, Long> {
}
