package org.example.crud.service;

import java.io.Serializable;
import java.util.List;

public interface BaseService<ID extends Serializable, T> {
    T findById(ID id);

    List<T> findAll();

    T save(T departmentDTO);

    T update(ID id, T departmentDTO);

    void delete(ID id);
}
