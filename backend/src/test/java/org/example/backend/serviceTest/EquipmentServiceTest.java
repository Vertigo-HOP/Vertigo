package org.example.backend.serviceTest;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.example.backend.model.Category;
import org.example.backend.model.Equipment;
import org.example.backend.repository.IEquipmentRepository;
import org.example.backend.service.EquipmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EquipmentServiceTest {

    @Mock
    private IEquipmentRepository equipmentRepository;

    @InjectMocks
    private EquipmentService equipmentService;

    private Equipment equipment;
    private Validator validator;

    @BeforeEach
    void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        equipment = new Equipment();
        equipment.setEquipmentId(1);
        equipment.setName("HDMI Cable");
        equipment.setCategory(Category.HDMI_CABLES);
        equipment.setLength(2.0);
        equipment.setWeight(0.2);
        equipment.setBrand("Samsung");
        equipment.setNumberOfUnits(20);
        equipment.setRepairUnits(0);
        equipment.setStoragedUnits(20);
    }

    @Test
    void addEquipmentSuccess(){
        when(equipmentRepository.save(equipment)).thenReturn(equipment);

        Equipment result = equipmentService.addEquipment(equipment);

        assertEquals(equipment, result);
        verify(equipmentRepository).save(equipment);
    }

    @Test
    void addEquipmentFail(){
        equipment.setLength(-5.2);

        var violations = validator.validate(equipment);

        assertFalse(violations.isEmpty());
    }


}
