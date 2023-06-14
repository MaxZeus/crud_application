package com.crudapp.repository;

import java.util.List;

public interface GenericRepository<T, ID> {
    T create(T t);
    List<T> readAll();
    T getById(ID id);
    T update(T t);
    boolean deleteById(ID id);
}
