package com.slowcloud.copypaste.mapper;

public interface AbstractMapper<E, D> {
    D toDto(E entity);
    E toEntity(D dto);
}
