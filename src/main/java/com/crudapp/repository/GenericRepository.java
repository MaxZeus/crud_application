package com.crudapp.repository;

public interface GenericRepository<T, ID> {
    void create();
    T read();
    void update();
    boolean delete();
    void save(T t);
    void setStatus();
}
