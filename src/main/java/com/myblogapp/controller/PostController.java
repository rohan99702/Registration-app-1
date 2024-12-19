package com.myblogapp.controller;

import com.myblogapp.payload.ListPostDto;
import com.myblogapp.payload.PostDto;
import com.myblogapp.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/myblogapp/post")
public class PostController
{
    @Autowired
    private PostService postService;

    //http://localhost:8080/api/myblogapp/post
    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto dto=postService.savePost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    //http://localhost:8080/api/myblogapp/post?pageNo=0&pageSize=2&sortBy=id&sortDir=asc
    @GetMapping
    public ResponseEntity<?> getAllPost(
            @RequestParam(name="sortBy",defaultValue ="id",required=true) String sortBy,
            @RequestParam(name="sortDir",defaultValue ="asc",required=true) String sortDir,
            @RequestParam(name="pageNo",defaultValue ="0",required=true) int pageNo,
            @RequestParam(name="pageSize",defaultValue ="2",required=true) int pageSize)
    {
        ListPostDto lpdto=postService.getPost(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(lpdto,HttpStatus.OK);
    }

}
