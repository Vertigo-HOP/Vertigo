package org.example.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
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

    @ManyToOne
    @JsonBackReference("equipment-reference")
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;

    @ManyToOne
    @JoinColumn (name = "packing_list_id", nullable = false)
    @JsonBackReference ("packinglist-reference")
    private PackingList packingList;

    private Integer quantity;
    private Integer packedQuantity;

}
