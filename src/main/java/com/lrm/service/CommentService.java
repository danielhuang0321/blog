package com.lrm.service;

import com.lrm.po.Comment;

import java.util.List;

/**
 * Created by Daniel on 2022/10/22.
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}
