package com.crudapp.view;

import com.crudapp.controller.PostController;
import com.crudapp.controller.WriterController;
import com.crudapp.model.Label;
import com.crudapp.model.Writer;

import java.util.Scanner;

public class WriterView {
    private final Scanner scanner = new Scanner(System.in);
    private final WriterController writerController = new WriterController();

    public void save() {
        System.out.println("Enter name, lastname");
        String name = scanner.nextLine();
        String lastName = scanner.nextLine();
        Writer result = writerController.saveWriter(name, lastName);
        System.out.println("Do you want to add posts to this writer?");
        String answer = scanner.nextLine();
        if (answer.equalsIgnoreCase("yes")) addPosts(result.getId());
        System.out.println(result);
    }
    public void readAll() {
        writerController.readWriters().forEach(System.out::println);
    }
    public void read(Integer index) {
        System.out.println(writerController.readWriter(index));
    }
    public void update(Integer index) {
        System.out.println(writerController.updateWriter(index));
    }
    public void delete(Integer index) {
        System.out.println(writerController.deleteWriter(index));
    }
    public void addPosts(Integer writerIndex) {
        writerController.addPosts(new PostController().readPosts(), writerIndex);
    }

}
