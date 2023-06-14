package com.crudapp.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter @Getter
@ToString
public class Label {
    private int id;
    private String name;
    private Status status;
}
