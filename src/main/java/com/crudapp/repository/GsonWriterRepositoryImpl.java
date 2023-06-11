package com.crudapp.repository;

import com.crudapp.model.Post;
import com.crudapp.model.Status;
import com.crudapp.model.Writer;
import com.google.gson.Gson;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class GsonWriterRepositoryImpl implements WriterRepository {
    private final Scanner scan = new Scanner(System.in);
    private final Writer writer = new Writer();

    @Override
    public void create() {

        System.out.println("Please enter writer information id/name/lastname/status(is active or not)");
        this.writer.setId(Integer.parseInt(scan.next()));
        this.writer.setFirstName(scan.nextLine());
        this.writer.setLastName(scan.nextLine());
        this.writer.setStatus(scan.nextLine().equals("active") ? Status.ACTIVE : Status.DELETED);

        save(this.writer);
    }
    @Override
    public Writer read() {
        Writer writer = null;
        if (!new File("writers.json").exists()) {
            System.out.println("Please create a writer first");
            create();
            return read();
        } else {
            try (FileReader fr = new FileReader("writers.json");
                 Scanner scan = new Scanner(fr)) {
                writer = new Gson().fromJson(scan.nextLine(), Writer.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return writer;
    }

    public void update() {
        System.out.println("Please enter new parameters for a writer: ");
        System.out.println("id/name/lastname/status(is active or not)");

        this.writer.setId(Integer.parseInt(scan.nextLine()));
        this.writer.setFirstName(scan.nextLine());
        this.writer.setLastName(scan.nextLine());
        this.writer.setStatus(scan.nextLine().equals("active") ? Status.ACTIVE : Status.DELETED);

        save(this.writer);
    }
    @Override
    public boolean delete() {
        return new File("writers.json").delete();
    }

    @Override
    public void save(Writer writer) {
        try (FileWriter fw = new FileWriter("writers.json")) {
            fw.write(new Gson().toJson(writer));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void setStatus() {
        System.out.println("Please set writer status: Active/Deleted)");
        this.writer.setStatus(scan.nextLine().equals("Active") ? Status.ACTIVE : Status.DELETED);

        save(this.writer);
    }
    @Override
    public void addPosts(List<Post> list) {
        this.writer.setPosts(list);
        save(this.writer);
    }
}
