package com.crudapp.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@ToString
public class Writer {
    private int id;
    private String firstName;
    private String lastName;
    private List<Post> posts;
    private Status status;
}
