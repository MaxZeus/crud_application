package com.crudapp.repository;

import com.crudapp.model.Label;
import com.crudapp.model.Post;
import com.crudapp.model.PostStatus;
import com.google.gson.Gson;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class GsonPostRepositoryImpl implements PostRepository {
    private final Scanner scan = new Scanner(System.in);
    private final Post post = new Post();

    @Override
    public void create() {

        System.out.println("Bellow this string you can write your post: ");
        System.out.println("id/content/created/updated(same as created, if you don't updated it yet");
        this.post.setId(Integer.parseInt(scan.next()));
        this.post.setContent(scan.nextLine());
        this.post.setCreated(scan.nextLine());
        this.post.setUpdated(scan.nextLine());

        save(this.post);
    }

    @Override
    public Post read() {
        Post post = null;

        if (!new File("posts.json").exists()) {
            System.out.println("Please create a post first");
            create();
            return read();
        } else {
            try (FileReader fr = new FileReader("posts.json");
                 Scanner scan = new Scanner(fr)) {
                post = new Gson().fromJson(scan.nextLine(), Post.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return post;
    }

    @Override
    public void update() {
        System.out.println("After updating your post please update data as well.");
        this.post.setContent(scan.nextLine());
        this.post.setUpdated(scan.nextLine());

        save(this.post);
    }

    @Override
    public boolean delete() {
        return new File("posts.json").delete();
    }

    @Override
    public void save(Post post) {
        try (FileWriter fw = new FileWriter("posts.json", true)) {
            fw.write(new Gson().toJson(post));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setStatus() {
        System.out.println("Please enter post status: active/under_review/deleted");
        String status = scan.nextLine();
        PostStatus postStatus = null;

        switch(status) {
            case "active" -> postStatus = PostStatus.ACTIVE;
            case "under_review" -> postStatus = PostStatus.UNDER_REVIEW;
            case "deleted" -> postStatus = PostStatus.DELETED;
        }
        this.post.setStatus(postStatus);
        save(this.post);
    }

    @Override
    public void addLabelList(List<Label> labelList) {
        this.post.setLabels(labelList);
        save(this.post);
    }
}
