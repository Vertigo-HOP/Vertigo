package org.example.backend.service;

import org.example.backend.dto.EquipmentRequestDTO;
import org.example.backend.dto.EquipmentResponseDTO;
import org.example.backend.exception.ResourceNotFoundException;
import org.example.backend.model.Equipment;
import org.example.backend.repository.IEquipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public EquipmentResponseDTO readEquipmentById (Integer equipmentId){
        Equipment equipment= equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipment not found with id: " + equipmentId));

        return new EquipmentResponseDTO(equipment.getEquipmentId(), equipment.getCategory(),
                equipment.getName(), equipment.getWeight(), equipment.getBrand(), equipment.getNumberOfUnits(),
                equipment.getRepairUnits(), equipment.getStoragedUnits());
    }

    public List<EquipmentResponseDTO> readAllEquipments (){
        List<Equipment> equipments = equipmentRepository.findAll();

        return equipments.stream()
                .map(equipment -> new EquipmentResponseDTO(
                        equipment.getEquipmentId(), equipment.getCategory(), equipment.getName(),
                        equipment.getWeight(), equipment.getBrand(), equipment.getNumberOfUnits(),
                        equipment.getRepairUnits(), equipment.getStoragedUnits()
                ))
                .toList();
    }



}
