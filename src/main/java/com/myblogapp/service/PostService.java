package com.myblogapp.service;

import com.myblogapp.payload.ListPostDto;
import com.myblogapp.payload.PostDto;

public interface PostService {
    PostDto savePost(PostDto postDto);
    ListPostDto getPost(int pageNo,int pageSize,String sortBy,String sortDir);
}
