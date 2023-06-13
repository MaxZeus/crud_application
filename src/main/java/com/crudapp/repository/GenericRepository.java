package com.crudapp.repository;

import java.util.List;

public interface GenericRepository<T, ID> {
    void create();
    List<T> read();
    void update(ID i) throws IndexOutOfBoundsException;
    boolean delete(ID i) throws IndexOutOfBoundsException;
    void save();
    void setStatus(T t);
}
