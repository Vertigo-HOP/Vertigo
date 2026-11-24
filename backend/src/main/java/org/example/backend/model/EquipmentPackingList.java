package org.example.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "equipment_packing_list")
@Getter
@Setter
@NoArgsConstructor
public class EquipmentPackingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer equipmentPackingListId;

    @ManyToOne
    @JoinColumn(name = "equipment_id", nullable = false)
    private Equipment equipment;

    @ManyToOne
    @JoinColumn (name = "packing_list_id", nullable = false)
    private PackingList packingList;

    private Integer quantity;
    private Integer packedQuantity;

}
