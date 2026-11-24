package org.example.backend.controller;

import jakarta.validation.Valid;
import org.example.backend.model.Equipment;
import org.example.backend.service.EquipmentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    private final EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @PostMapping("/add")
    public Equipment equipment(@Valid @RequestBody Equipment equipment){
        return equipmentService.addEquipment(equipment);

    }


}
