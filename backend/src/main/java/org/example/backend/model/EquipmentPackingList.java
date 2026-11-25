package org.example.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "equipment_packing_list")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EquipmentPackingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer equipmentPackingListId;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @NotNull
    @ManyToOne
    @JoinColumn (name = "packing_list_id")
    @JsonBackReference ("packinglist-reference")
    private PackingList packingList;

    @NotNull
    @PositiveOrZero
    private Integer quantity;
    @NotNull
    @PositiveOrZero
    private Integer packedQuantity;

}
