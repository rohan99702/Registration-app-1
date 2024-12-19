package com.myblogapp.payload;

import java.util.List;

public class PostWithCommentDto
{
    private PostDto postdto;
    private List<CommentDto> commentdto;

    public PostDto getPostdto() {return postdto;}

    public void setPostdto(PostDto postdto) {this.postdto = postdto;}

    public List<CommentDto> getCommentdto() {return commentdto;}

    public void setCommentdto(List<CommentDto> commentdto) {this.commentdto = commentdto;}
}
