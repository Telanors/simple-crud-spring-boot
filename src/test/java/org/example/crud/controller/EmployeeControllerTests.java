package org.example.crud.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.crud.dto.EmployeeDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getAllDepartments() {
        try {
            var response = restTemplate.getForEntity("/api/employees", String.class);
            assertEquals(HttpStatus.OK, response.getStatusCode());

            var employees = Arrays.asList(objectMapper.readValue(response.getBody(), EmployeeDTO[].class));
            assertEquals(2, employees.size());
            assertEquals("Михаил", employees.get(0).getFirstname());
        } catch (JsonProcessingException e) {
            fail("JSON processing error: " + e.getMessage());
        }
    }

    @Test
    void getDepartmentById() {
        try {
            var response = restTemplate.getForEntity("/api/employees/1", String.class);
            assertEquals(HttpStatus.OK, response.getStatusCode());

            var employee = objectMapper.readValue(response.getBody(), EmployeeDTO.class);
            assertEquals(1, employee.getId());
            assertEquals("Михаил", employee.getFirstname());
        } catch (JsonProcessingException e) {
            fail("JSON processing error: " + e.getMessage());
        }
    }
}
