package com.crudapp.repository;

import com.crudapp.model.Post;
import com.crudapp.model.Status;
import com.crudapp.model.Writer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GsonWriterRepositoryImpl implements WriterRepository {
    private final Scanner scan = new Scanner(System.in);
    private final List<Writer> writerList = new ArrayList<>();

    @Override
    public void create() {
        Writer writer = new Writer();
        System.out.println("Please enter writer information id/name/lastname/status(is active or not)");
        writer.setId(Integer.parseInt(scan.nextLine()));
        writer.setFirstName(scan.nextLine());
        writer.setLastName(scan.nextLine());
        writer.setStatus(scan.nextLine().equals("active") ? Status.ACTIVE : Status.DELETED);

        writerList.add(writer);
    }
    @Override
    public List<Writer> read() {

        if (!new File("writers.json").exists()) {
            System.out.println("Please create a writer first");
            create();
            return read();
        } else {
            try (FileReader fr = new FileReader("writers.json");
                 Scanner scan = new Scanner(fr)) {

                Type writerType = new TypeToken<List<Writer>>(){}.getType();
                writerList.addAll(new Gson().fromJson(scan.nextLine(), writerType));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return writerList;
    }
    @Override
    public void update(Integer number) {
        try {
            Writer writer = writerList.get(number);
            System.out.println("Please enter new parameters for a writer: ");
            System.out.println("Enter name for : name/lastname or status(is active or not)");

            if (scan.nextLine().equals("name")) {
                writer.setFirstName(scan.nextLine());
                writer.setLastName(scan.nextLine());
            } else writer.setStatus(scan.nextLine().equals("active") ? Status.ACTIVE : Status.DELETED);

        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is no such writer at that index");
        }
    }
    @Override
    public boolean delete(Integer number) {
        try {
            writerList.remove((int) number);
            return true;
        } catch (IndexOutOfBoundsException e){
            System.out.println("There is no such writer at that index");
            return false;
        }
    }

    @Override
    public void save() {
        try (FileWriter fw = new FileWriter("writers.json")) {
            fw.write(new Gson().toJson(writerList));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void setStatus(Writer writer) {
        System.out.println("Please set writer status: Active/Deleted)");
        writer.setStatus(scan.nextLine().equals("Active") ? Status.ACTIVE : Status.DELETED);

    }
    @Override
    public void addPosts(List<Post> list, Integer number) {
        writerList.get(number).setPosts(list);
    }
}
