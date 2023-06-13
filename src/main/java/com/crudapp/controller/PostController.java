package com.crudapp.controller;

import com.crudapp.model.Label;
import com.crudapp.model.Post;
import com.crudapp.repository.GsonPostRepositoryImpl;
import com.crudapp.view.PostView;

import java.util.List;

public class PostController extends AbstractController<Post, Integer>{
    private final GsonPostRepositoryImpl postRepCont;

    public PostController() {
        this.postRepCont = new GsonPostRepositoryImpl();
        setGenericRepositoryAndView(postRepCont, new PostView());
    }

    public void addLabelList(List<Label> lableList, Integer postID) {
        postRepCont.addLabelList(lableList, postID);
    }
}
