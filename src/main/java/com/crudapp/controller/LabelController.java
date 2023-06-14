package com.crudapp.controller;

import com.crudapp.model.Label;
import com.crudapp.model.Status;
import com.crudapp.repository.GenericRepository;
import com.crudapp.repository.gson.GsonLabelRepositoryImpl;

import java.util.List;
import java.util.Scanner;

public class LabelController {
    private final GenericRepository<Label, Integer> labelRepository = new GsonLabelRepositoryImpl();
    private final Scanner scan = new Scanner(System.in);

    public Label saveLabel(String name) {
        Label label = new Label();
        label.setName(name);
        label.setStatus(Status.ACTIVE);
        return labelRepository.create(label);
    }
    public List<Label> readLabels() {
        return labelRepository.readAll();
    }
    public Label readLabel(Integer number) {
        return readLabels().get(number);
    }
    public Label updateLabel(Integer number) {
        Label label = labelRepository.getById(number);
        System.out.println("Please enter new name and status active/deleted");
        label.setName(scan.nextLine());
        label.setStatus(scan.nextLine().equals("active") ? Status.ACTIVE : Status.DELETED);
        return labelRepository.update(label);
    }
    public boolean deleteLabel(Integer number) {
        return labelRepository.deleteById(number);
    }

}
