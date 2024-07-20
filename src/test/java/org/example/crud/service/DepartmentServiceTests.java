package org.example.crud.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.crud.dto.DepartmentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class DepartmentServiceTests {
    @Autowired
    private DepartmentService departmentService;

    @Test
    void getAllDepartments() {
        var departments = departmentService.findAll();
        assertEquals(2, departments.size());
    }

    @Test
    void getDepartmentById() {
        var department = departmentService.findById(1);
        assertNotNull(department);
        assertEquals("OOP", department.getName());
    }

    @Test
    void createDepartment() {
        var newDepartmentDTO = new DepartmentDTO(null, "Finance");

        var createdDepartmentDTO = departmentService.save(newDepartmentDTO);
        assertNotNull(createdDepartmentDTO.getId());
        assertEquals("Finance", createdDepartmentDTO.getName());
    }

    @Test
    void updateDepartment() {
        var departmentDTO = new DepartmentDTO(1, "Operations");
        var updatedDepartmentDTO = departmentService.update(1, departmentDTO);

        assertEquals("Operations", updatedDepartmentDTO.getName());
    }

    @Test
    void deleteDepartment() {
        departmentService.delete(1);
        assertThrows(EntityNotFoundException.class, () -> departmentService.findById(1));
    }
}
