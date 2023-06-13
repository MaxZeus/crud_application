package com.crudapp.controller;

import com.crudapp.model.Label;
import com.crudapp.repository.GsonLabelRepositoryImpl;
import com.crudapp.view.LabelView;

public class LabelController extends AbstractController<Label, Integer> {

    public LabelController() {
        setGenericRepositoryAndView(new GsonLabelRepositoryImpl(), new LabelView());
    }
}
