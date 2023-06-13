package com.crudapp.controller;

import com.crudapp.model.Post;
import com.crudapp.model.Writer;
import com.crudapp.repository.GenericRepository;
import com.crudapp.repository.GsonWriterRepositoryImpl;
import com.crudapp.view.WriterView;

import java.util.List;


public class WriterController extends AbstractController<Writer, Integer> {
    private final GsonWriterRepositoryImpl writerRepCont;

    public WriterController() {
        this.writerRepCont = new GsonWriterRepositoryImpl();
        setGenericRepositoryAndView(writerRepCont, new WriterView());
    }
    public void addPostsList(List<Post> postList, Integer writerId) {
        writerRepCont.addPosts(postList, writerId);
    }
}
