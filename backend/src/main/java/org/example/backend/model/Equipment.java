package org.example.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer equipmentId;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Category category;

    @NotBlank
    private String name;

    @PositiveOrZero
    private Double weight;

    private String brand;

    @NotNull
    @PositiveOrZero
    private Integer numberOfUnits;

    @NotNull
    @PositiveOrZero
    private Integer repairUnits;

    @NotNull
    @PositiveOrZero
    private Integer storagedUnits;

}
