package org.example.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class PackingList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer packingListId;

    @Column(nullable = false)
    private String title;

    private LocalDate startDate;
    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private PackingListStatus status;

    private String comment;

    @OneToMany(mappedBy = "packingList")
    private List<EquipmentPackingList> equipmentPackingList;
}
