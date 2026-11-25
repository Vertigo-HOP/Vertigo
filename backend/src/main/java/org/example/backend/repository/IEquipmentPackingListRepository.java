package org.example.backend.repository;

import org.example.backend.model.EquipmentPackingList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEquipmentPackingListRepository extends JpaRepository<EquipmentPackingList, Integer> {
}
