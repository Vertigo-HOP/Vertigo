package org.example.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.example.backend.model.Category;

public record EquipmentRequestDTO(@NotNull
                                  Category category,

                                  @NotBlank
                                  String name,

                                  @PositiveOrZero
                                  Double weight,

                                  String brand,

                                  @NotNull
                                  @PositiveOrZero
                                  Integer numberOfUnits,

                                  @NotNull
                                  @PositiveOrZero
                                  Integer repairUnits,

                                  @NotNull
                                  @PositiveOrZero
                                  Integer storagedUnits) {
}
