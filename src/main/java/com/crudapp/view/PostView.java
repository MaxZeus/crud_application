package com.crudapp.view;

import com.crudapp.model.Label;
import com.crudapp.model.Post;

import java.util.List;


public class PostView {
    public void showPost(Post post) {
        System.out.printf("Post: id %d\n Content[ %s ]\nCreated %s\n Updated %s\n Post status %s", post.getId(), post.getContent(), post.getCreated(), post.getUpdated(), post.getStatus());
        post.getLabels().forEach(new LabelView()::showLabel);
    }
}
