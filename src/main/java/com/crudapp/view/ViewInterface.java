package com.crudapp.view;

import java.util.List;

public interface ViewInterface<T> {
    void show(List<T> t);
}
