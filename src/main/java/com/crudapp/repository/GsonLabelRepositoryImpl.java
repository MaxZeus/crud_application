package com.crudapp.repository;

import com.crudapp.model.Label;
import com.crudapp.model.Status;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GsonLabelRepositoryImpl implements LabelRepository{
    private final Scanner scan = new Scanner(System.in);
    private final List<Label> labelList = new ArrayList<Label>();


    @Override
    public void create() {
        Label label = new Label();
        System.out.println("Please enter an id and label name");
        label.setId(Integer.parseInt(scan.nextLine()));
        label.setName(scan.nextLine());
        setStatus(label);

        labelList.add(label);
    }

    @Override
    public List<Label> read() {

       if (!new File("labels.json").exists()) {
           System.out.println("Please create a label first");
           create();
           return read();
       } else {
           try (FileReader fr = new FileReader("labels.json");
                Scanner scan = new Scanner(fr)) {

               Type labelType = new TypeToken<List<Label>>(){}.getType();
               labelList.addAll(new Gson().fromJson(scan.nextLine(), labelType));
           } catch (IOException e) {
               throw new RuntimeException(e);
           }
       }
       return labelList;
    }

    @Override
    public void update(Integer number) {
        try {
            Label label = labelList.get(number);
            System.out.println("You can update id or label name");
            System.out.println("What do you want to change? name/status");

            if (scan.nextLine().equals("status")) setStatus(label);
            else label.setName(scan.nextLine());

        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is no such label at that index");
        }
    }

    @Override
    public boolean delete(Integer number) {
        try {
            labelList.remove((int)number);
            return true;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("There is no such label at that index");
            return false;
        }
    }

    @Override
    public void save() {
        try (FileWriter fw = new FileWriter("labels.json")) {
            fw.write(new Gson().toJson(labelList));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void setStatus(Label label) {
        System.out.println("Please change the label status active/deleted");
        label.setStatus(scan.nextLine().equals("active") ? Status.ACTIVE : Status.DELETED);
    }
}
