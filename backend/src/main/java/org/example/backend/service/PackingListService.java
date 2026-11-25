package org.example.backend.service;

import org.example.backend.dto.EquipmentPackingListRequestDTO;
import org.example.backend.dto.EquipmentPackingListResponseDTO;
import org.example.backend.dto.PackingListRequestDTO;
import org.example.backend.dto.PackingListResponseDTO;
import org.example.backend.model.Equipment;
import org.example.backend.model.EquipmentPackingList;
import org.example.backend.model.PackingList;
import org.example.backend.repository.IEquipmentPackingListRepository;
import org.example.backend.repository.IEquipmentRepository;
import org.example.backend.repository.IPackingListRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PackingListService {

    private final IPackingListRepository packingListRepository;
    private final IEquipmentRepository equipmentRepository;
    private final IEquipmentPackingListRepository equipmentPackingListRepository;

    public PackingListService(IPackingListRepository packingListRepository, IEquipmentRepository equipmentRepository, IEquipmentPackingListRepository equipmentPackingListRepository) {
        this.packingListRepository = packingListRepository;
        this.equipmentRepository = equipmentRepository;
        this.equipmentPackingListRepository = equipmentPackingListRepository;
    }

    public PackingListResponseDTO createPackingList(PackingListRequestDTO requestDTO) {

        if (requestDTO.endDate().isBefore(requestDTO.startDate())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "End date cannot be before start date"
            );
        }

        PackingList packingList = new PackingList();

        packingList.setTitle(requestDTO.title());
        packingList.setStartDate(requestDTO.startDate());
        packingList.setEndDate(requestDTO.endDate());
        packingList.setStatus(requestDTO.status());
        packingList.setComment(requestDTO.comment());

        PackingList createdPackingList = packingListRepository.save(packingList);

        return new PackingListResponseDTO(
                createdPackingList.getPackingListId(),
                createdPackingList.getTitle(),
                createdPackingList.getStartDate(),
                createdPackingList.getEndDate(),
                createdPackingList.getStatus(),
                createdPackingList.getComment()
        );
    }

    public EquipmentPackingListResponseDTO createEquipment( 
            Integer packingListId,
            EquipmentPackingListRequestDTO requestDTO) {
        if (requestDTO.quantity() <= 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Quantity must be greater than zero"
            );
        }

        PackingList packingList = packingListRepository.findById(packingListId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Packing list not found"));

        Equipment equipment = equipmentRepository.findById(requestDTO.equipmentId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Equipment not found"));

        EquipmentPackingList equipmentPackingList = new EquipmentPackingList();

        equipmentPackingList.setPackingList(packingList);
        equipmentPackingList.setEquipment(equipment);
        equipmentPackingList.setQuantity(requestDTO.quantity());
        equipmentPackingList.setPackedQuantity(0);

        EquipmentPackingList createdEquipmentPackingList = equipmentPackingListRepository.save(equipmentPackingList);

        return new EquipmentPackingListResponseDTO(
                createdEquipmentPackingList.getEquipmentPackingListId(),
                createdEquipmentPackingList.getEquipment().getEquipmentId(),
                createdEquipmentPackingList.getQuantity(),
                createdEquipmentPackingList.getPackedQuantity()
        );
    }
}
