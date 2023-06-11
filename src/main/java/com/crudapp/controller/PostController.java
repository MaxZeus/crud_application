package com.crudapp.controller;

import com.crudapp.model.Label;
import com.crudapp.model.Post;
import com.crudapp.repository.GsonPostRepositoryImpl;

import java.util.List;

public class PostController extends AbstractController<Post, Integer>{
    private final GsonPostRepositoryImpl postRepCont;

    PostController() {
        this.postRepCont = new GsonPostRepositoryImpl();
        setGenericRepository(postRepCont);
    }

    public void addLabelList(List<Label> lableList) {
        postRepCont.addLabelList(lableList);
    }
}
