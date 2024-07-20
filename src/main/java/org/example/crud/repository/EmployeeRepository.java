package org.example.crud.repository;

import org.example.crud.entity.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends BaseRepository<Employee, Integer> {
}
