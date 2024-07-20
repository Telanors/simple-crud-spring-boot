package org.example.crud.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.crud.dto.DepartmentDTO;
import org.example.crud.dto.EmployeeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class EmployeeServiceTests {

    @Autowired
    private EmployeeService employeeService;

    @Test
    void getAllEmployees() {
        var employees = employeeService.findAll();
        assertEquals(2, employees.size());
    }

    @Test
    void getEmployeeById() {
        var employee = employeeService.findById(1);
        assertNotNull(employee);
        assertEquals("Михаил", employee.getFirstname());
    }

    @Test
    void createEmployee() {
        var department = DepartmentDTO.builder()
                .id(1)
                .name("OOP")
                .build();

        var employee = EmployeeDTO.builder()
                .firstname("Антон")
                .lastname("Антонович")
                .address("Маркса 12")
                .department(department)
                .build();

        var createdEmployeeDTO = employeeService.save(employee);
        assertNotNull(createdEmployeeDTO.getId());
        assertEquals("Антон", createdEmployeeDTO.getFirstname());
    }

    @Test
    void updateEmployee() {
        var department = DepartmentDTO.builder()
                .id(1)
                .name("OOP")
                .build();

        var employee = EmployeeDTO.builder()
                .firstname("Антон")
                .lastname("Иванович")
                .address("Маркса 24")
                .department(department)
                .build();
        var updatedEmployeeDTO = employeeService.update(1, employee);

        assertEquals("Антон", updatedEmployeeDTO.getFirstname());
        assertEquals("Иванович", updatedEmployeeDTO.getLastname());
        assertEquals("Маркса 24", updatedEmployeeDTO.getAddress());
    }

    @Test
    void deleteEmployee() {
        employeeService.delete(1);
        assertThrows(EntityNotFoundException.class, () -> employeeService.findById(1));
    }
}
