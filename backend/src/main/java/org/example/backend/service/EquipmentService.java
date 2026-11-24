package org.example.backend.service;

import org.example.backend.exception.ResourceCreationException;
import org.example.backend.model.Equipment;
import org.example.backend.repository.IEquipmentRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService {

    private final IEquipmentRepository equipmentRepository;

    public EquipmentService(IEquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public Equipment addEquipment (Equipment equipment){
            return equipmentRepository.save(equipment);
    }



}
