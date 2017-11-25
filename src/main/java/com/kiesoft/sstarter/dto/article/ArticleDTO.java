package com.kiesoft.sstarter.dto.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kiesoft.sstarter.domain.article.Article;
import com.kiesoft.sstarter.dto.AbstractDTO;
import com.kiesoft.sstarter.dto.user.UserDTO;

public class ArticleDTO extends AbstractDTO implements Article<UserDTO> {

    private String body;

    @JsonIgnore
    private UserDTO owner;

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public UserDTO getOwner() {
        return owner;
    }

    @Override
    public void setOwner(UserDTO user) {
        this.owner = owner;
    }

}
