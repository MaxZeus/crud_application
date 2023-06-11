package com.crudapp.controller;

import com.crudapp.model.Label;
import com.crudapp.repository.GsonLabelRepositoryImpl;

public class LabelController extends AbstractController<Label, Integer>{

    LabelController() {
        setGenericRepository(new GsonLabelRepositoryImpl());
    }
}
