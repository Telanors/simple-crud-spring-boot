package org.example.crud.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.crud.dto.DepartmentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DepartmentControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void getAllDepartments() {
        try {
            var response = restTemplate.getForEntity("/api/departments", String.class);
            assertEquals(HttpStatus.OK, response.getStatusCode());

            var departments = Arrays.asList(objectMapper.readValue(response.getBody(), DepartmentDTO[].class));
            assertEquals(2, departments.size());
            assertEquals("HR", departments.get(0).getName());
        } catch (JsonProcessingException e) {
            fail("JSON processing error: " + e.getMessage());
        }
    }

    @Test
    void getDepartmentById() {
        try {
            var response = restTemplate.getForEntity("/api/departments/1", String.class);
            assertEquals(HttpStatus.OK, response.getStatusCode());

            var department = objectMapper.readValue(response.getBody(), DepartmentDTO.class);
            assertEquals(1, department.getId());
            assertEquals("OOP", department.getName());
        } catch (JsonProcessingException e) {
            fail("JSON processing error: " + e.getMessage());
        }
    }
}
