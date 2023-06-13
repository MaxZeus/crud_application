package com.crudapp;

import com.crudapp.controller.LabelController;
import com.crudapp.controller.PostController;
import com.crudapp.controller.WriterController;

import java.util.Scanner;

public class Main {
    public static void main( String[] args ) {
        Scanner scan = new Scanner(System.in);
        String entity, operation;

        WriterController writerController = new WriterController();
        PostController postController = new PostController();
        LabelController labelController = new LabelController();

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
                        case "create" -> writerController.create();
                        case "read" -> writerController.read();
                        case "update" -> {
                            System.out.println("Please enter an ID: ");
                            int id = Integer.parseInt(scan.nextLine());

                            writerController.update(id);
                        }
                        case "delete" -> {
                            System.out.println("Please enter an ID: ");
                            int id = Integer.parseInt(scan.nextLine());

                            writerController.delete(id);
                        }
                    }

                }
                case "post" -> {
                    switch(operation) {
                        case "create" -> postController.create();
                        case "read" -> postController.read();
                        case "update" -> {
                            System.out.println("Please enter an ID");
                            int id = Integer.parseInt(scan.nextLine());

                            postController.update(id);
                        }
                        case "delete" -> {
                            System.out.println("Please enter an ID");
                            int id = Integer.parseInt(scan.nextLine());

                            postController.delete(id);
                        }
                    }
                }
                case "label" -> {
                    switch(operation) {
                        case "create" -> labelController.create();
                        case "read" -> labelController.read();
                        case "update" -> {
                            System.out.println("Please enter an ID");
                            int id = Integer.parseInt(scan.nextLine());

                            labelController.update(id);
                        }
                        case "delete" -> {
                            System.out.println("Please enter an ID");
                            int id = Integer.parseInt(scan.nextLine());

                            labelController.delete(id);
                        }
                    }
                }
            }

            System.out.println("Do you want to apply labels to post or add posts to writer labels/posts");
            String addListOption = scan.nextLine().toLowerCase();

            if (addListOption.equals("labels")) {
                System.out.println("Please enter a post id: ");
                postController.addLabelList(labelController.getList(), Integer.parseInt(scan.nextLine()));
            } else if (addListOption.equals("posts")) {
                System.out.println("Please enter a writer id: ");
                writerController.addPostsList(postController.getList(), Integer.parseInt(scan.nextLine()));
            }

            System.out.println("Do you want to ESC? yes/no");
        } while (!scan.nextLine().equals("yes"));

        writerController.save();
        postController.save();
        labelController.save();
    }
}
