package com.myblogapp.service.impl;

import com.myblogapp.entity.Post;
import com.myblogapp.payload.ListPostDto;
import com.myblogapp.payload.PostDto;
import com.myblogapp.repository.PostRepository;
import com.myblogapp.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceimpl implements PostService
{
    @Autowired
    private PostRepository postrepo;
    @Autowired
    private ModelMapper modelmapper;

    @Override

    public PostDto savePost(PostDto postDto)
    {
      Post post=maptoEntity(postDto);
      return maptoDto(postrepo.save(post));
    }

    @Override
    public ListPostDto getPost(int pageNo, int pageSize, String sortBy, String sortDir)
    {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?
                Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);
        Page<Post> post=postrepo.findAll(pageable);
        List<Post> posts=post.getContent();
        List<PostDto> dtos=posts.stream().map(n->maptoDto(n)).collect(Collectors.toList());

        ListPostDto lpdto=new ListPostDto();
        lpdto.setListpost(dtos);
        lpdto.setFirstPage(post.isFirst());
        lpdto.setLastPage(post.isLast());
        lpdto.setPageNumber(post.getNumber());
        lpdto.setTotalElement((int)post.getTotalElements());
        lpdto.setTotalPage(post.getTotalPages());

        return lpdto;
    }

    Post maptoEntity(PostDto dto){return modelmapper.map(dto,Post.class);}
    PostDto maptoDto(Post post){return modelmapper.map(post,PostDto.class);}
}
