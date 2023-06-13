package com.crudapp.view;

import com.crudapp.model.Label;
import com.crudapp.model.Post;

import java.util.List;


public class PostView implements ViewInterface<Post> {
    public void show(List<Post> postList) {
        postList.forEach(post -> {
            System.out.printf("Post: id %d\nContent[ %s ]\nCreated %s\nUpdated %s\nPost status %s\n", post.getId(), post.getContent(), post.getCreated(), post.getUpdated(), post.getStatus());
            if (post.getLabels() == null) System.out.println("There are no labels for this post");
            else new LabelView().show(post.getLabels());
        });

    }
}
