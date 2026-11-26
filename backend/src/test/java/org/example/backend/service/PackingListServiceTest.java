package org.example.backend.service;

import org.example.backend.dto.PackingListRequestDTO;
import org.example.backend.dto.PackingListResponseDTO;
import org.example.backend.model.PackingList;
import org.example.backend.model.PackingListStatus;
import org.example.backend.repository.IPackingListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PackingListServiceTest {

    @Mock
    private IPackingListRepository packingListRepository;

    @InjectMocks
    private PackingListService packingListService;

    private PackingListRequestDTO requestDTO;
    private PackingList packingList;

    @BeforeEach
    void setUp() {

        // ARRANGE
        requestDTO = new PackingListRequestDTO(
                "Roskilde Festival",
                LocalDate.of(2027, 6, 25),
                LocalDate.of(2027, 6, 28),
                PackingListStatus.DRAFT,
                "Test Pakkeliste"
        );
        packingList = new PackingList();
        packingList.setPackingListId(1);
        packingList.setTitle(requestDTO.title());
        packingList.setStartDate(requestDTO.startDate());
        packingList.setEndDate(requestDTO.endDate());
        packingList.setStatus(requestDTO.status());
        packingList.setComment(requestDTO.comment());
    }

    @Test
    void addPackingListSuccess() {

        // ARRANGE
        when(packingListRepository.save(any(PackingList.class)))
                .thenReturn(packingList);

        // ACT
        PackingListResponseDTO result = packingListService.createPackingList(requestDTO);

        // ASSERT
        assertNotNull(result);
        assertEquals(1, result.packingListId());
        assertEquals("Roskilde Festival", result.title());
        assertEquals(LocalDate.of(2027, 6, 25), result.startDate());
        assertEquals(LocalDate.of(2027, 6, 28), result.endDate());
        assertEquals(PackingListStatus.DRAFT, result.status());
        assertEquals("Test Pakkeliste", result.comment());

        verify(packingListRepository, times(1)).save(any(PackingList.class));
    }

    @Test
    void addPackingListFail() {

        // ARRANGE
        when(packingListRepository.save(any(PackingList.class)))
                .thenThrow(new RuntimeException("Database Error"));

        // ACT & ASSERT
        assertThrows(RuntimeException.class, () -> {
            packingListService.createPackingList(requestDTO);
        });

        verify(packingListRepository).save(any(PackingList.class));
    }

    @Test
    void addPackingListFailWhenEndDateIsBeforeStartDate() {

        // ARRANGE
        PackingListRequestDTO invalidRequestDTO = new PackingListRequestDTO(
                "Roskilde Festival",
                LocalDate.of(2027, 6, 28),
                LocalDate.of(2027, 6, 25),
                PackingListStatus.DRAFT,
                "Test pakkeliste"
        );

        // ACT & ASSERT
        assertThrows(ResponseStatusException.class, () ->
                packingListService.createPackingList(invalidRequestDTO));

        verify(packingListRepository, never()).save(any(PackingList.class));
    }
}