package com.crudapp.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@ToString
public class Post {
    private int id;
    private String content;
    private String created;
    private String updated;
    private List<Label> labels;
    private PostStatus status;
}
