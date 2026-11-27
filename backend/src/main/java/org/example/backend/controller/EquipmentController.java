package org.example.backend.controller;

import jakarta.validation.Valid;
import org.example.backend.dto.EquipmentRequestDTO;
import org.example.backend.dto.EquipmentResponseDTO;
import org.example.backend.service.EquipmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PostMapping("/create")
    public ResponseEntity<EquipmentResponseDTO> createEquipment(@Valid @RequestBody EquipmentRequestDTO request){
        EquipmentResponseDTO response = equipmentService.createEquipment(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/{equipmentId}")
    public ResponseEntity<EquipmentResponseDTO> readEquipmentById (@PathVariable Integer equipmentId){
        EquipmentResponseDTO response = equipmentService.readEquipmentById(equipmentId);
        return ResponseEntity.ok(response);
    }


}
