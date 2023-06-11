package com.crudapp.repository;

import com.crudapp.model.Label;
import com.crudapp.model.Status;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GsonLabelRepositoryImpl implements LabelRepository{
    private final Scanner scan = new Scanner(System.in);
    private final Label label = new Label();


    @Override
    public void create() {
        System.out.println("Please enter an id and label name");
        this.label.setId(Integer.parseInt(scan.nextLine()));
        this.label.setName(scan.nextLine());

        save(this.label);
    }

    @Override
    public Label read() {
       Label label = null;

       if (!new File("labels.json").exists()) {
           System.out.println("Please create a label first");
           create();
           return read();
       } else {
           try (FileReader fr = new FileReader("labels.json");
                Scanner scan = new Scanner(fr)) {
               label = new Gson().fromJson(scan.nextLine(), Label.class);
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
       }
       return label;
    }

    @Override
    public void update() {
        System.out.println("You can update id or label name");
        this.label.setId(Integer.parseInt(scan.nextLine()));
        this.label.setName(scan.nextLine());

        save(this.label);
    }

    @Override
    public boolean delete() {
        return new File("labels.json").delete();
    }

    @Override
    public void save(Label label) {
        try (FileWriter fw = new FileWriter("labels.json")) {
            fw.write(new Gson().toJson(label));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setStatus() {
        System.out.println("Please change the label status active/deleted");
        this.label.setStatus(scan.nextLine().equals("active") ? Status.ACTIVE : Status.DELETED);
        save(this.label);
    }
}
