package org.dy.dao;

import org.dy.bean.Comment;

import java.util.List;

public interface CommentDao {
    /*分页查询评论列白*/
    List<Comment> selectByPage(Comment comment);

    /*新增评论信息*/
    int insert(Comment comment);
}
