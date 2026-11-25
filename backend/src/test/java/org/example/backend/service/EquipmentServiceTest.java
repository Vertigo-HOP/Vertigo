package org.example.backend.service;

import org.example.backend.dto.EquipmentRequestDTO;
import org.example.backend.dto.EquipmentResponseDTO;
import org.example.backend.model.Category;
import org.example.backend.model.Equipment;
import org.example.backend.repository.IEquipmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EquipmentServiceTest {

    @Mock
    private IEquipmentRepository equipmentRepository;

    @InjectMocks
    private EquipmentService equipmentService;

    private Equipment equipment;
    private EquipmentRequestDTO request;

    @BeforeEach
    void setUp(){
        request = new EquipmentRequestDTO(Category.HDMI_CABLES,
                "HDMI Cable",
                0.2,
                "Samsung",
                20,
                0,
                20);

        equipment = new Equipment();
        equipment.setEquipmentId(1);
        equipment.setName("HDMI Cable");
        equipment.setCategory(Category.HDMI_CABLES);
        equipment.setWeight(0.2);
        equipment.setBrand("Samsung");
        equipment.setNumberOfUnits(20);
        equipment.setRepairUnits(0);
        equipment.setStoragedUnits(20);
    }

    @Test
    void createEquipmentSuccess(){
        //arrange
        when(equipmentRepository.save(any(Equipment.class))).thenReturn(equipment);

        //act
        EquipmentResponseDTO result = equipmentService.addEquipment(request);

       //assert
        assertNotNull(result);
        assertEquals(equipment.getEquipmentId(), result.equipmentId());
        assertEquals(equipment.getCategory(), result.category());
        assertEquals(equipment.getName(), result.name());
        assertEquals(equipment.getWeight(), result.weight());
        assertEquals(equipment.getBrand(), result.brand());
        assertEquals(equipment.getNumberOfUnits(), result.numberOfUnits());
        assertEquals(equipment.getRepairUnits(), result.repairUnits());
        assertEquals(equipment.getStoragedUnits(), result.storagedUnits());

        verify(equipmentRepository, times(1)).save(any(Equipment.class));
    }

    @Test
    void createEquipmentFail(){
        // arrange
        when(equipmentRepository.save(any(Equipment.class)))
                .thenThrow(new RuntimeException("Error"));

        // act & assert
        assertThrows(RuntimeException.class, () -> {
            equipmentService.addEquipment(request);
        });

        verify(equipmentRepository, times(1)).save(any(Equipment.class));
    }


}
