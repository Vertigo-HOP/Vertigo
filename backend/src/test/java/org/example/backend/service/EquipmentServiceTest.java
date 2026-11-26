package org.example.backend.service;

import org.example.backend.dto.EquipmentRequestDTO;
import org.example.backend.dto.EquipmentResponseDTO;
import org.example.backend.exception.ResourceNotFoundException;
import org.example.backend.model.Category;
import org.example.backend.model.Equipment;
import org.example.backend.repository.IEquipmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

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
    private Equipment equipment2;
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

        equipment2 = new Equipment();
        equipment2.setEquipmentId(2);
        equipment2.setName("ceiling lamp");
        equipment2.setCategory(Category.LAMPS);
        equipment2.setWeight(1.5);
        equipment2.setBrand("Bosch");
        equipment2.setNumberOfUnits(5);
        equipment2.setRepairUnits(1);
        equipment2.setStoragedUnits(4);
    }

    @Test
    void createEquipmentSuccess(){
        //arrange
        when(equipmentRepository.save(any(Equipment.class))).thenReturn(equipment);

        //act
        EquipmentResponseDTO result = equipmentService.createEquipment(request);

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
            equipmentService.createEquipment(request);
        });

        verify(equipmentRepository, times(1)).save(any(Equipment.class));
    }

    @Test
    void readEquipmentByIdSuccess(){
        //arrange
        when(equipmentRepository.findById(1)).thenReturn(Optional.of(equipment));

        //act
        EquipmentResponseDTO result = equipmentService.readEquipmentById(1);

        //assert
        assertEquals(equipment.getEquipmentId(), result.equipmentId());
        assertEquals(equipment.getName(), result.name());
        assertEquals(equipment.getCategory(), result.category());
        assertEquals(equipment.getWeight(), result.weight());
        assertEquals(equipment.getBrand(), result.brand());
        assertEquals(equipment.getNumberOfUnits(), result.numberOfUnits());
        assertEquals(equipment.getRepairUnits(), result.repairUnits());
        assertEquals(equipment.getStoragedUnits(), result.storagedUnits());

        verify(equipmentRepository).findById(1);
    }

    @Test
    void readEquipmentByIdFail(){
        //arrange
        when(equipmentRepository.findById(10)).thenReturn(Optional.empty());

        //act & assert
        assertThrows(ResourceNotFoundException.class, () -> {equipmentService.readEquipmentById(10);});

        verify(equipmentRepository, times(1)).findById(10);
    }

    @Test
    void readAllEquipmentsSuccess(){
        //arrange


        when(equipmentRepository.findAll()).thenReturn(List.of(equipment, equipment2));

        //act
        List<EquipmentResponseDTO> result = equipmentService.readAllEquipments();

        //assert
        assertEquals(2, result.size());

        assertEquals(equipment.getEquipmentId(), result.get(0).equipmentId());
        assertEquals(equipment.getCategory(), result.get(0).category());
        assertEquals(equipment.getName(), result.get(0).name());
        assertEquals(equipment.getWeight(), result.get(0).weight());
        assertEquals(equipment.getBrand(), result.get(0).brand());
        assertEquals(equipment.getNumberOfUnits(), result.get(0).numberOfUnits());
        assertEquals(equipment.getRepairUnits(), result.get(0).repairUnits());
        assertEquals(equipment.getStoragedUnits(), result.get(0).storagedUnits());

        assertEquals(equipment2.getEquipmentId(), result.get(1).equipmentId());
        assertEquals(equipment2.getCategory(), result.get(1).category());
        assertEquals(equipment2.getName(), result.get(1).name());
        assertEquals(equipment2.getWeight(), result.get(1).weight());
        assertEquals(equipment2.getBrand(), result.get(1).brand());
        assertEquals(equipment2.getNumberOfUnits(), result.get(1).numberOfUnits());
        assertEquals(equipment2.getRepairUnits(), result.get(1).repairUnits());
        assertEquals(equipment2.getStoragedUnits(), result.get(1).storagedUnits());

        verify(equipmentRepository, times(1)).findAll();
    }

    @Test
    void readAllEquipmentsFail(){
        //arrange
        when(equipmentRepository.findAll()).thenThrow(new RuntimeException("Error"));

        //act & assert
        assertThrows(RuntimeException.class, () -> {equipmentService.readAllEquipments();});

        verify(equipmentRepository, times(1)).findAll();

    }


}
