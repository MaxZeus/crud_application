package com.crudapp.view;


import com.crudapp.model.Writer;

import java.util.List;

public class WriterView implements ViewInterface<Writer> {
    @Override
    public void show(List<Writer> writerList) {
        writerList.forEach(writer -> {
            System.out.printf("Writer: ID %d\n Name %s %s\n Status %s\n", writer.getId(), writer.getFirstName(), writer.getLastName(), writer.getStatus());
            if (writer.getPosts() == null) System.out.println("This writer hasn't posted anything yet");
            else new PostView().show(writer.getPosts());
        });

    }
}
