package com.kiesoft.sstarter.jpa.entity.user;

import com.kiesoft.sstarter.domain.user.User;
import com.kiesoft.sstarter.jpa.entity.AbstractEntity;
import com.kiesoft.sstarter.jpa.entity.article.ArticleEntity;
import com.kiesoft.sstarter.jpa.entity.role.RoleEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sstarter_user", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@NamedEntityGraphs({
        @NamedEntityGraph(name = "findOne", attributeNodes = {
                @NamedAttributeNode(value = "roles"),
                @NamedAttributeNode(value = "articles")
        })
})
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserEntity extends AbstractEntity implements User<RoleEntity, ArticleEntity> {

    private String username;
    private String password;
    private Boolean enabled;
    private Set<RoleEntity> roles = new HashSet<>();
    private Set<ArticleEntity> articles = new HashSet<>();

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Boolean getEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @ManyToMany
    @JoinTable(
            name = "sstarter_user_roles",
            joinColumns = {@JoinColumn(name = "idUser")},
            inverseJoinColumns = {@JoinColumn(name = "idRole")})
    @Override
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public Set<RoleEntity> getRoles() {
        return roles;
    }

    @Override
    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    @Override
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    public Set<ArticleEntity> getArticles() {
        return articles;
    }

    @Override
    public void setArticles(Set<ArticleEntity> articles) {
        this.articles = articles;
    }

    @Override
    public void addArticle(ArticleEntity article) {
        article.setOwner(this);
        this.articles.add(article);
    }

    @Override
    public void removeArticle(ArticleEntity article) {
        article.setOwner(null);
        this.articles.remove(article);
    }

}
