package org.example.backend.dto;

import org.example.backend.model.PackingListStatus;

import java.time.LocalDate;
import java.util.List;

public record PackingListResponseDTO(
        Integer packingListId,
        String title,
        LocalDate startDate,
        LocalDate endDate,
        PackingListStatus status,
        String comment
) {
}
