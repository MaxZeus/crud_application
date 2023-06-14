package com.crudapp.view;

import com.crudapp.controller.LabelController;

import java.util.Scanner;

public class MainView {
    private final Scanner scan = new Scanner(System.in);
    private String entity, operation;
    private final LabelView labelView = new LabelView();
    private final PostView postView = new PostView();
    private final WriterView writerView = new WriterView();

    public void start() {
        do {
            System.out.println("What type of object do you want to work with");
            System.out.println("Writer card(enter writer) || Post(enter post) || Label(enter label)");
            entity = scan.nextLine().toLowerCase();
            System.out.println("What operation are you planning to do?");
            System.out.println("Create/Read/Update/Delete");
            operation = scan.nextLine().toLowerCase();

            switch(entity) {
                case "writer" -> {
                    switch(operation) {
                        case "create" -> {
                            writerView.save();
                        }
                        case "read" -> {
                            System.out.println("Do you want to read one or all writers?");
                            if (scan.nextLine().equals("all")) {
                                writerView.readAll();
                            } else {
                                System.out.println("Enter index");
                                Integer index = Integer.parseInt(scan.nextLine());
                                writerView.read(index);
                            }
                        }
                        case "update" -> {
                            System.out.println("Enter index");
                            Integer index = Integer.parseInt(scan.nextLine());
                            writerView.update(index);
                        }
                        case "delete" -> {
                            System.out.println("Enter index");
                            Integer index = Integer.parseInt(scan.nextLine());
                            writerView.delete(index);
                        }
                    }
                }
                case "post" -> {
                    switch(operation) {
                        case "create" -> postView.save();
                        case "read" -> {
                            System.out.println("Do you want to read one or all posts?");
                            if (scan.nextLine().equals("all")) {
                                postView.readAll();
                            } else {
                                System.out.println("Enter index");
                                Integer index = Integer.parseInt(scan.nextLine());
                                postView.read(index);
                            }
                        }
                        case "update" -> {
                            System.out.println("Enter index");
                            Integer index = Integer.parseInt(scan.nextLine());
                            postView.update(index);
                        }
                        case "delete" -> {
                            System.out.println("Enter index");
                            Integer index = Integer.parseInt(scan.nextLine());
                            postView.delete(index);
                        }
                    }
                }

                case "label" -> {
                    switch(operation) {
                        case "create" -> labelView.save();
                        case "read" -> {
                            System.out.println("Do you want to read one or all labels?");
                            if (scan.nextLine().equals("all")) {
                                labelView.readAll();
                            } else {
                                System.out.println("Enter index");
                                Integer index = Integer.parseInt(scan.nextLine());
                                labelView.read(index);
                            }
                        }
                        case "update" -> {
                            System.out.println("Enter index");
                            Integer index = Integer.parseInt(scan.nextLine());
                            labelView.update(index);
                        }
                        case "delete" -> {
                            System.out.println("Enter index");
                            Integer index = Integer.parseInt(scan.nextLine());
                            labelView.delete(index);
                        }
                    }
                }

            }
            System.out.println("Do you want to ESC? yes/no");
        } while (!scan.nextLine().equals("yes"));
    }
}
