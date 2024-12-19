package com.myblogapp.controller;

import com.myblogapp.payload.CommentDto;
import com.myblogapp.payload.PostWithCommentDto;
import com.myblogapp.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/myblogapp/comment")
public class CommentController
{
    @Autowired
    private CommentService commentService;

    //http://localhost:8080/api/myblogapp/comment/1
    @PostMapping("/{id}")
    public ResponseEntity<?> createComment(@RequestBody CommentDto dto,
                                           @PathVariable long id)
    {
        CommentDto commentDto=commentService.saveComment(dto,id);
        return new ResponseEntity<>(commentDto, HttpStatus.CREATED);
    }
    //http://localhost:8080/api/myblogapp/comment?id=1
    @GetMapping
    public ResponseEntity<?> getCommentsByPostId(@RequestParam("id") long id)
    {
        PostWithCommentDto pcdto=commentService.getCommentByPost(id);
        return new ResponseEntity<>(pcdto,HttpStatus.OK);
    }
}
