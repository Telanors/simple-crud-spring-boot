package org.example.crud.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.crud.dto.DepartmentDTO;
import org.example.crud.entity.Department;
import org.example.crud.mapper.DepartmentMapper;
import org.example.crud.repository.DepartmentRepository;
import org.example.crud.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public DepartmentDTO findById(Integer id) {
        var department = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found"));

        return departmentMapper.toDTO(department);
    }

    @Override
    public List<DepartmentDTO> findAll() {
        return departmentRepository.findAll().stream()
                .map(departmentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDTO save(DepartmentDTO departmentDTO) {
        var department = departmentMapper.toEntity(departmentDTO);
        return departmentMapper.toDTO(departmentRepository.save(department));
    }

    @Override
    public DepartmentDTO update(Integer id, DepartmentDTO departmentDTO) {
        var department = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found"));

        department.setName(departmentDTO.getName());
        return departmentMapper.toDTO(departmentRepository.save(department));
    }

    @Override
    public void delete(Integer id) {
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Department not found"));

        departmentRepository.delete(department);
    }
}
