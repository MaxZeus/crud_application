package com.crudapp.view;

import com.crudapp.model.Label;

import java.util.List;

public class LabelView {
    public void showLabel(Label label) {
        System.out.printf("Label ID: %d Label name: %s Label status: %s\n", label.getId(), label.getName(), label.getStatus());
    }
}
