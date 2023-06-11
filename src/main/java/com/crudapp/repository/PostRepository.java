package com.crudapp.repository;

import com.crudapp.model.Label;
import com.crudapp.model.Post;

import java.util.List;

public interface PostRepository extends GenericRepository<Post, Integer>{
        void addLabelList(List<Label> labelList);
}
