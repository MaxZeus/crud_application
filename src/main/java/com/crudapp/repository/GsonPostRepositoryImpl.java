package com.crudapp.repository;

import com.crudapp.model.Label;
import com.crudapp.model.Post;
import com.crudapp.model.PostStatus;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GsonPostRepositoryImpl implements PostRepository {
    private final Scanner scan = new Scanner(System.in);
    private final List<Post> postList = new ArrayList<>();

    @Override
    public void create() {
        Post post = new Post();
        System.out.println("Bellow this string you can write your post: ");
        System.out.println("id/content/created");

        post.setId(Integer.parseInt(scan.nextLine()));
        post.setContent(scan.nextLine());

        String dateOfCreationAndUpdating = scan.nextLine();
        post.setCreated(dateOfCreationAndUpdating);
        post.setUpdated(dateOfCreationAndUpdating);

        setStatus(post);

        postList.add(post);
    }

    @Override
    public List<Post> read() {

        if (!new File("posts.json").exists()) {
            System.out.println("Please create a post first");
            create();
            return read();
        } else {
            try (FileReader fr = new FileReader("posts.json");
                 Scanner scan = new Scanner(fr)) {

                Type typeList = new TypeToken<ArrayList<Post>>(){}.getType();
                this.postList.addAll(new Gson().fromJson(scan.nextLine(), typeList));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return postList;
    }

    @Override
    public void update(Integer number) {
        Post post = postList.get(number);
        System.out.println("After updating your post please update data as well.");
        System.out.println("What do you want to update? id/content/status");


        if (scan.nextLine().equals("status")) setStatus(post);
        else post.setContent(scan.nextLine());

        post.setUpdated(scan.nextLine());

    }

    @Override
    public boolean delete(Integer number) {
        try {
            postList.remove((int)number);
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is no post with such index");
            return false;
        }

    }

    @Override
    public void save() {
        try (FileWriter fw = new FileWriter("posts.json")) {
            fw.write(new Gson().toJson(this.postList));
            fw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setStatus(Post post) {
        System.out.println("Please enter post status: active/under_review/deleted");
        String status = scan.nextLine().toLowerCase();
        PostStatus postStatus = null;

        switch(status) {
            case "active" -> postStatus = PostStatus.ACTIVE;
            case "under_review" -> postStatus = PostStatus.UNDER_REVIEW;
            case "deleted" -> postStatus = PostStatus.DELETED;
        }
        post.setStatus(postStatus);
    }

    @Override
    public void addLabelList(List<Label> labelList, Integer postNumber) {
        this.postList.get(postNumber).setLabels(labelList);
    }
}
