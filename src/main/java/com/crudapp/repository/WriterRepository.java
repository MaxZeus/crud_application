package com.crudapp.repository;

import com.crudapp.model.Post;
import com.crudapp.model.Writer;

import java.util.List;

public interface WriterRepository extends GenericRepository<Writer, Integer> {
    void addPosts(List<Post> list, Integer number);
}
