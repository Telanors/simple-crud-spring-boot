package org.example.crud.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.crud.dto.EmployeeDTO;
import org.example.crud.entity.Department;
import org.example.crud.mapper.EmployeeMapper;
import org.example.crud.repository.EmployeeRepository;
import org.example.crud.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeDTO findById(Integer id) {
        var employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        return employeeMapper.toDTO(employee);
    }

    @Override
    public List<EmployeeDTO> findAll() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO save(EmployeeDTO employeeDTO) {
        var employee = employeeMapper.toEntity(employeeDTO);
        return employeeMapper.toDTO(employeeRepository.save(employee));
    }

    @Override
    public EmployeeDTO update(Integer id, EmployeeDTO employeeDTO) {
        var employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        employee.setFirstname(employeeDTO.getFirstname());
        employee.setLastname(employeeDTO.getLastname());
        employee.setAddress(employeeDTO.getAddress());
        employee.setSalary(employeeDTO.getSalary());
        employee.setDepartment(Department.builder()
                .id(employeeDTO.getDepartment().getId())
                .name(employeeDTO.getDepartment().getName())
                .build());

        return employeeMapper.toDTO(employeeRepository.save(employee));
    }

    @Override
    public void delete(Integer id) {
        var employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        employeeRepository.delete(employee);
    }
}
