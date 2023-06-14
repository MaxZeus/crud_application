package com.crudapp.controller;

import com.crudapp.model.Label;
import com.crudapp.model.PostStatus;
import com.crudapp.model.Post;
import com.crudapp.repository.GenericRepository;
import com.crudapp.repository.gson.GsonPostRepositoryImpl;

import java.util.List;
import java.util.Scanner;

public class PostController {
    private final GenericRepository<Post, Integer> postRepository = new GsonPostRepositoryImpl();
    private final Scanner scan = new Scanner(System.in);

    public Post savePost(String content, String created) {
        Post post = new Post();
        post.setContent(content);
        post.setCreated(created);
        post.setUpdated(created);
        post.setStatus(PostStatus.ACTIVE);
        return postRepository.create(post);
    }
    public List<Post> readPosts() {
        return postRepository.readAll();
    }
    public Post readPost(Integer number) {
        return readPosts().get(number);
    }
    public Post updatePost(Integer number) {
        Post post = postRepository.getById(number);
        System.out.println("Please enter new content and update time and status active/under_review/deleted");
        post.setContent(scan.nextLine());
        post.setUpdated(scan.nextLine());
        String postStatus = scan.nextLine().toLowerCase();
        post.setStatus(postStatus.equals("active") ? PostStatus.ACTIVE : postStatus.equals("deleted") ? PostStatus.DELETED : PostStatus.UNDER_REVIEW);
        return postRepository.update(post);
    }
    public boolean deletePost(Integer number) {
        return postRepository.deleteById(number);
    }
    public void addLabels(List<Label> list, Integer postNumber) {
        Post post = readPost(postNumber);
        post.setLabels(list);
        postRepository.update(post);
    }
}
