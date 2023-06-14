package com.crudapp.view;

import com.crudapp.controller.LabelController;
import com.crudapp.controller.PostController;
import com.crudapp.model.Label;
import com.crudapp.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PostView {
    private final Scanner scanner = new Scanner(System.in);
    private final PostController postController = new PostController();

    public void save() {
        System.out.println("Content and date of creation");
        String content = scanner.nextLine();
        String date = scanner.nextLine();
        Post result = postController.savePost(content, date);
        System.out.println("Do you want to add labels to this post?");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("yes")) addLabels(result.getId());
        System.out.println(result);
    }
    public void readAll() {
        postController.readPosts().forEach(System.out::println);
    }
    public void read(Integer index) {
        System.out.println(postController.readPost(index));
    }
    public void update(Integer index) {
        System.out.println(postController.updatePost(index));
    }
    public void delete(Integer index) {
        System.out.println(postController.deletePost(index));
    }
    public void addLabels(Integer postIndex) {
        postController.addLabels(new LabelController().readLabels(), postIndex);
    }
}
