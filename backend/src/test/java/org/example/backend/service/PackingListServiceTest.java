package org.example.backend.service;

import org.example.backend.model.PackingList;
import org.example.backend.model.PackingListStatus;
import org.example.backend.repository.IPackingListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PackingListServiceTest {

    @Mock
    private IPackingListRepository packingListRepository;

    @InjectMocks
    private PackingListService packingListService;

    private PackingList packingList;

    @BeforeEach
    void setUp() {
        // ARRANGE
        packingList = new PackingList();
        packingList.setTitle("Roskilde Festival");
        packingList.setStartDate(LocalDate.of(2027, 6, 25));
        packingList.setEndDate(LocalDate.of(2027, 6, 28));
        packingList.setStatus(PackingListStatus.DRAFT);
        packingList.setComment("Test pakkeliste");
    }

    @Test
    void addPackingListSuccess() {

        // ARRANGE
        when(packingListRepository.save(packingList))
                .thenReturn(packingList);

        // ACT
        PackingList result = packingListService.createPackingList(packingList);

        // ASSERT
        assertNotNull(result);
        assertEquals("Roskilde Festival", result.getTitle());
        assertEquals(LocalDate.of(2027, 6, 25), result.getStartDate());
        assertEquals(LocalDate.of(2027, 6, 28), result.getEndDate());
        assertEquals(PackingListStatus.DRAFT, result.getStatus());
        assertEquals("Test pakkeliste", result.getComment());

        verify(packingListRepository, times(1)).save(packingList);
    }

    @Test
    void addPackingListFail() {

        // ARRANGE
        when(packingListRepository.save(packingList))
                .thenThrow(new RuntimeException("Database Error"));

        // ACT & ASSERT
        assertThrows(RuntimeException.class, () -> {
            packingListService.createPackingList(packingList);
        });

        verify(packingListRepository).save(packingList);
    }
}