package com.kiesoft.sstarter.jpa.entity.article;

import com.kiesoft.sstarter.domain.article.Article;
import com.kiesoft.sstarter.jpa.entity.AbstractEntity;
import com.kiesoft.sstarter.jpa.entity.user.UserEntity;

import javax.persistence.*;

@Entity
@Table(name = "sstarter_article")
public class ArticleEntity extends AbstractEntity implements Article<UserEntity> {

    private String body;
    private UserEntity owner;

    @Column(length = 5000)
    @Override
    public String getBody() {
        return body;
    }

    @Override
    public void setBody(String body) {
        this.body = body;
    }

    @ManyToOne
    @Override
    public UserEntity getOwner() {
        return owner;
    }

    @Override
    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

}
