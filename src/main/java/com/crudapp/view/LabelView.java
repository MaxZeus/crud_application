package com.crudapp.view;

import com.crudapp.controller.LabelController;
import com.crudapp.model.Label;

import java.util.Scanner;

public class LabelView  {

    private final Scanner scanner = new Scanner(System.in);
    private final LabelController labelController = new LabelController();

    public void save() {
        System.out.println("enter name");
        String name = scanner.nextLine();
        Label result = labelController.saveLabel(name);
        System.out.println(result);
    }
    public void readAll() {
        labelController.readLabels().forEach(System.out::print);
    }
    public void read(Integer index) {
        System.out.println(labelController.readLabel(index));
    }
    public void update(Integer index) {
        System.out.println(labelController.updateLabel(index));
    }
    public void delete(Integer index) {
        System.out.println(labelController.deleteLabel(index));
    }
}
