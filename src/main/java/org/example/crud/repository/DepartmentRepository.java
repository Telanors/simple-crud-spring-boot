package org.example.crud.repository;

import org.example.crud.entity.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends BaseRepository<Department, Integer> {
}
