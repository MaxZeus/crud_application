package com.crudapp.controller;

import com.crudapp.model.Writer;
import com.crudapp.repository.GenericRepository;

public abstract class AbstractController <T, ID>{
    GenericRepository<T, ID> genericRepository;

    public void setGenericRepository(GenericRepository<T, ID> genericRepository) {
        this.genericRepository = genericRepository;
    }

    public void create() {
        genericRepository.create();
    }
    public T read() {
        return genericRepository.read();
    }
    public void update() {
        genericRepository.update();
    }
    public void delete() {
        genericRepository.delete();
    }
    public void setStatus() {
       genericRepository.setStatus();
    }
}
