package org.example.backend.dto;

import jakarta.validation.constraints.NotNull;

public record EquipmentPackingListRequestDTO(
        @NotNull
        Integer equipmentId,
        @NotNull
        int quantity
) {}

