package com.crudapp.view;


import com.crudapp.model.Writer;

public class WriterView {
    public void showWriter(Writer writer) {
        System.out.printf("Writer: ID %d\n Name %s %s\n Status %s\n", writer.getId(), writer.getFirstName(), writer.getLastName(), writer.getStatus());
        writer.getPosts().forEach(new PostView()::showPost);
    }
}
