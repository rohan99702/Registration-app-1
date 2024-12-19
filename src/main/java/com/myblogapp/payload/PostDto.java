package com.myblogapp.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PostDto
{
    private String id;
    @NotEmpty
    @Size(min=3,message="title should be atleast 3 characters")
    private String title;
    @NotEmpty
    @Size(min=5,message="content should be at least 5 characters")
    private String content;
    private String description;

    public String getId() {return id;}

    public void setId(String id) {
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
