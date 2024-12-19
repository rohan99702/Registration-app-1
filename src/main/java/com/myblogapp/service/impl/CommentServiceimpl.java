package com.myblogapp.service.impl;

import com.myblogapp.entity.Comment;
import com.myblogapp.entity.Post;
import com.myblogapp.payload.CommentDto;
import com.myblogapp.payload.PostDto;
import com.myblogapp.payload.PostWithCommentDto;
import com.myblogapp.repository.CommentRepository;
import com.myblogapp.repository.PostRepository;
import com.myblogapp.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceimpl implements CommentService
{
    @Autowired
    private CommentRepository commentrepo;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;

    Comment maptoEntity(CommentDto dto){return modelMapper.map(dto,Comment.class);}
    CommentDto maptoDto(Comment comment){return modelMapper.map(comment,CommentDto.class);}
    PostDto maptoPostDto(Post post){return modelMapper.map(post,PostDto.class);}

    @Override
    public CommentDto saveComment(CommentDto dto, long id)
    {
        Post post=postRepository.findById(id).get();
        Comment comment=maptoEntity(dto);
        comment.setPost(post);
        Comment savedcomment=commentrepo.save(comment);
        return maptoDto(savedcomment);

    }

    @Override
    public PostWithCommentDto getCommentByPost(long id)
    {
        Post post=postRepository.findById(id).get();
        PostDto postDto=maptoPostDto(post);
        List<Comment> comment=commentrepo.findByPostId(id);
        List<CommentDto> commentdto=comment.stream().map(n->maptoDto(n)).collect(Collectors.toList());

        PostWithCommentDto pcdto=new PostWithCommentDto();
        pcdto.setPostdto(postDto);
        pcdto.setCommentdto(commentdto);

        return pcdto;
    }
}
