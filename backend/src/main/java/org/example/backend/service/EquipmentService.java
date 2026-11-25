package org.example.backend.service;

import org.example.backend.dto.EquipmentRequestDTO;
import org.example.backend.dto.EquipmentResponseDTO;
import org.example.backend.model.Equipment;
import org.example.backend.repository.IEquipmentRepository;
import org.springframework.stereotype.Service;

@Service
public class EquipmentService {

    private final IEquipmentRepository equipmentRepository;

    public EquipmentService(IEquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public EquipmentResponseDTO createEquipment(EquipmentRequestDTO request){
        Equipment equipment = new Equipment();

        equipment.setCategory(request.category());
        equipment.setBrand(request.brand());
        equipment.setName(request.name());
        equipment.setWeight(request.weight());
        equipment.setNumberOfUnits(request.numberOfUnits());
        equipment.setRepairUnits(request.repairUnits());
        equipment.setStoragedUnits(request.storagedUnits());

        Equipment createdEquipment = equipmentRepository.save(equipment);

        return new EquipmentResponseDTO(createdEquipment.getEquipmentId(), createdEquipment.getCategory(),
                createdEquipment.getName(), createdEquipment.getWeight(), createdEquipment.getBrand(),
                createdEquipment.getNumberOfUnits(), createdEquipment.getRepairUnits(), createdEquipment.getStoragedUnits());

    }



}
