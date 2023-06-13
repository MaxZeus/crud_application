package com.crudapp.view;

import com.crudapp.model.Label;

import java.util.List;

public class LabelView implements ViewInterface<Label> {
    @Override
    public void show(List<Label> list) {
        list.forEach(label -> System.out.printf("Label ID: %d\nLabel name: %s\nLabel status: %s\n", label.getId(), label.getName(), label.getStatus()));
    }
}
