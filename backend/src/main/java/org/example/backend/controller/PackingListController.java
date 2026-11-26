package org.example.backend.controller;

import jakarta.validation.Valid;
import org.example.backend.dto.EquipmentPackingListRequestDTO;
import org.example.backend.dto.EquipmentPackingListResponseDTO;
import org.example.backend.dto.PackingListRequestDTO;
import org.example.backend.dto.PackingListResponseDTO;
import org.example.backend.service.PackingListService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/packinglist")
public class PackingListController {

    private final PackingListService packingListService;

    public PackingListController(PackingListService packingListService) {
        this.packingListService = packingListService;
    }

    @PostMapping("/create")
    public ResponseEntity<PackingListResponseDTO> packingList(@Valid @RequestBody PackingListRequestDTO requestDTO) {
        PackingListResponseDTO responseDTO = packingListService.createPackingList(requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @PostMapping("/{id}/equipment")
    public ResponseEntity<EquipmentPackingListResponseDTO> addEquipment(@PathVariable Integer id, @Valid @RequestBody EquipmentPackingListRequestDTO requestDTO) {
        EquipmentPackingListResponseDTO responseDTO = packingListService.createEquipment(id, requestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
