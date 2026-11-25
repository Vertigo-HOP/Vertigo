package org.example.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.backend.model.PackingListStatus;

import java.time.LocalDate;

public record PackingListRequestDTO(
        @NotBlank
        String title,

        @NotNull
        LocalDate startDate,

        @NotNull
        LocalDate endDate,

        @NotNull
        PackingListStatus status,

        String comment
        ) {
}
