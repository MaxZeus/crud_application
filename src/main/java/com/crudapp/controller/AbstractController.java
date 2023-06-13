package com.crudapp.controller;

import com.crudapp.model.Writer;
import com.crudapp.repository.GenericRepository;
import com.crudapp.view.ViewInterface;

import java.util.List;

public abstract class AbstractController <T, ID>{
    GenericRepository<T, ID> genericRepository;
    ViewInterface<T> tViewInterface;

    public void setGenericRepositoryAndView(GenericRepository<T, ID> genericRepository, ViewInterface<T> tViewInterface) {
        this.genericRepository = genericRepository;
        this.tViewInterface = tViewInterface;
    }

    public void create() {
        genericRepository.create();
    }
    public void read() {
        tViewInterface.show(genericRepository.read());
    }
    public void update(ID id) {
        genericRepository.update(id);
    }
    public void delete(ID id) {
        genericRepository.delete(id);
    }
    public void save() {genericRepository.save();}
    public void setStatus(T t) {
       genericRepository.setStatus(t);
    }
    public List<T> getList() {
        return genericRepository.read();
    }
}
