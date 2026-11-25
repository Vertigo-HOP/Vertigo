package org.example.backend.dto;

import jakarta.validation.constraints.NotNull;

public record EquipmentPackingListResponseDTO(
        @NotNull
        Integer id,
        @NotNull
        Integer equipmentId,
        @NotNull
        int quantity,
        @NotNull
        int packedQuantity
) {
}
