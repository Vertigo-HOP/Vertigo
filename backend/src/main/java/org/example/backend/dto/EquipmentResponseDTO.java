package org.example.backend.dto;

import org.example.backend.model.Category;

public record EquipmentResponseDTO(Integer equipmentId,
                                   Category category,
                                   String name,
                                   Double weight,
                                   String brand,
                                   Integer numberOfUnits,
                                   Integer repairUnits,
                                   Integer storagedUnits) {
}
