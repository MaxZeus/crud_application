package com.crudapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
public class Post {
    private int id;
    private String content;
    private String created;
    private String updated;
    private List<Label> labels;
    PostStatus status;
}
