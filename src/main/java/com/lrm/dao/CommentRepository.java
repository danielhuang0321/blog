package com.lrm.dao;

import com.lrm.po.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Daniel on 2022/10/22.
 */
public interface CommentRepository extends JpaRepository<Comment,Long>{


    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
}
