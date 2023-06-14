package com.crudapp.controller;


import com.crudapp.model.Post;
import com.crudapp.model.Status;
import com.crudapp.model.Writer;
import com.crudapp.repository.GenericRepository;
import com.crudapp.repository.gson.GsonWriterRepositoryImpl;

import java.util.List;
import java.util.Scanner;

public class WriterController {
    private final GenericRepository<Writer, Integer> writerRepository = new GsonWriterRepositoryImpl();
    private final Scanner scan = new Scanner(System.in);

    public Writer saveWriter(String name, String lastName) {
        Writer writer = new Writer();
        writer.setFirstName(name);
        writer.setLastName(lastName);
        writer.setStatus(Status.ACTIVE);
        return writerRepository.create(writer);
    }
    public List<Writer> readWriters() {
        return writerRepository.readAll();
    }
    public Writer readWriter(Integer number) {
        return readWriters().get(number);
    }
    public Writer updateWriter(Integer number) {
        Writer writer = writerRepository.getById(number);
        System.out.println("Please enter new name and lastname and status active/deleted");
        writer.setFirstName(scan.nextLine());
        writer.setLastName(scan.nextLine());
        writer.setStatus(scan.nextLine().equals("active") ? Status.ACTIVE : Status.DELETED);
        return writerRepository.update(writer);
    }
    public boolean deleteWriter(Integer number) {
        return writerRepository.deleteById(number);
    }
    public void addPosts(List<Post> posts, Integer number) {
        Writer writer = readWriter(number);
        writer.setPosts(posts);
        writerRepository.update(writer);
    }
}
