package org.example.backend.repository;


import org.example.backend.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEquipmentRepository extends JpaRepository<Equipment, Integer> {

}
