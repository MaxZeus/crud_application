package com.crudapp.repository.gson;

import com.crudapp.model.Post;
import com.crudapp.repository.GenericRepository;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GsonPostRepositoryImpl implements GenericRepository<Post, Integer> {
    private final String FILE_PATH = "posts.json";
    private final Gson GSON = new Gson();
    @Override
    public Post create(Post post) {
        List<Post> currentPosts = readPostsFromFile();
        Integer newId = generateId(currentPosts);
        post.setId(newId);
        currentPosts.add(post);
        writePostToFile(currentPosts);
        return post;
    }

    @Override
    public List<Post> readAll() {
        return readPostsFromFile();
    }

    @Override
    public Post getById(Integer number) {
        return readPostsFromFile().stream().filter(p -> p.getId() == number)
                .findFirst().orElse(null);
    }

    @Override
    public Post update(Post postToUpdate) {
        List<Post> currentPosts = readPostsFromFile().stream()
                .map(existingPost -> {
                    if (existingPost.getId() == postToUpdate.getId()) {
                        return postToUpdate;
                    }
                    return existingPost;
                }).collect(Collectors.toList());

        writePostToFile(currentPosts);
        return postToUpdate;
    }

    @Override
    public boolean deleteById(Integer number) {
        List<Post> posts = readPostsFromFile();
        posts.removeIf(p -> p.getId() == number);
        writePostToFile(posts);
        return true;
    }
    private List<Post> readPostsFromFile() {
        try (FileReader fr = new FileReader(FILE_PATH);
        Scanner scan = new Scanner(fr)) {
            Type postType = new TypeToken<List<Post>>(){}.getType();
            return GSON.fromJson(scan.nextLine(), postType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
    private Integer generateId(List<Post> posts) {
        return posts.stream().mapToInt(Post::getId).max().orElse(1);
    }

    private void writePostToFile(List<Post> posts) {
        try (FileWriter fw = new FileWriter(FILE_PATH)){
            fw.write(GSON.toJson(posts));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
