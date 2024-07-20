package org.example.crud.service;

import org.example.crud.dto.BaseDTO;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface BaseService<ID extends Serializable, T> {
    T findById(ID id);

    List<T> findAll();

    T save(T departmentDTO);

    T update(ID id, T departmentDTO);

    void delete(ID id);
}
