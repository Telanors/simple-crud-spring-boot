package org.example.crud.mapper;

import org.example.crud.dto.DepartmentDTO;
import org.example.crud.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DepartmentMapper extends BaseMapper<Department, DepartmentDTO> {
    @Override
    DepartmentDTO toDTO(Department entity);

    @Override
    @Mapping(target = "employees", ignore = true)
    Department toEntity(DepartmentDTO dto);
}