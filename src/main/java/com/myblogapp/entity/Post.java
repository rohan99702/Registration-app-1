package com.myblogapp.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="post")
public class Post
{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    private String description;
    @OneToMany(mappedBy = "post",orphanRemoval = true,cascade=CascadeType.ALL)
    private List<Comment> comments=new ArrayList<>();

    public List<Comment> getComments() {return comments;}

    public void setComments(List<Comment> comments) {this.comments = comments;}

    public long getId() {return id;}

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
