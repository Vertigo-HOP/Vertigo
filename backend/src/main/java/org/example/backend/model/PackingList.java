package org.example.backend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank
    private String title;

    @NotNull
    private LocalDate startDate;
    @NotNull
    private LocalDate endDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PackingListStatus status;

    private String comment;

    @OneToMany(mappedBy = "packingList")
    @JsonManagedReference("packinglist-reference")
    private List<EquipmentPackingList> equipmentPackingLists;
}
