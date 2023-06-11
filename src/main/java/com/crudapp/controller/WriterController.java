package com.crudapp.controller;

import com.crudapp.model.Post;
import com.crudapp.model.Writer;
import com.crudapp.repository.GenericRepository;
import com.crudapp.repository.GsonWriterRepositoryImpl;

import java.util.List;


public class WriterController extends AbstractController<Writer, Integer> {
    private final GsonWriterRepositoryImpl writerRepCont;

    WriterController() {
        this.writerRepCont = new GsonWriterRepositoryImpl();
        setGenericRepository(writerRepCont);
    }
    public void addPostsList(List<Post> postList) {
        writerRepCont.addPosts(postList);
    }
}
