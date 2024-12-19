package com.myblogapp.service;

import com.myblogapp.payload.CommentDto;
import com.myblogapp.payload.PostWithCommentDto;

public interface CommentService
{
    CommentDto saveComment(CommentDto dto, long id);

    PostWithCommentDto getCommentByPost(long id);
}
