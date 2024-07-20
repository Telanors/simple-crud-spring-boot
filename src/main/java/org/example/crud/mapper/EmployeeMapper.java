package org.example.crud.mapper;

import org.example.crud.dto.EmployeeDTO;
import org.example.crud.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmployeeMapper extends BaseMapper<Employee, EmployeeDTO> {
    @Override
    @Mapping(source = "id", target = "id")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "firstname", target = "firstname")
    @Mapping(source = "lastname", target = "lastname")
    @Mapping(source = "salary", target = "salary")
    @Mapping(source = "department", target = "department")
    EmployeeDTO toDTO(Employee entity);

    @Override
    @Mapping(target = "department.employees", ignore = true)
    Employee toEntity(EmployeeDTO dto);
}