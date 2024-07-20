package org.example.crud.mapper;

import org.example.crud.entity.BaseEntity;

import java.io.Serializable;

public interface BaseMapper<T extends BaseEntity<? extends Serializable>, F> {
    F toDTO(T entity);

    T toEntity(F dto);
}
