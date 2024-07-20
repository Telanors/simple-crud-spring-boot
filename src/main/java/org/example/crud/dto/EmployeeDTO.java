package org.example.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO implements BaseDTO<Integer> {
    private Integer id;
    private String firstname;
    private String lastname;
    private String address;
    private Double salary;
    private DepartmentDTO department;
}
