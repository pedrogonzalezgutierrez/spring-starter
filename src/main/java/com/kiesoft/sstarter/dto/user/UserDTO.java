package com.kiesoft.sstarter.dto.user;

import com.kiesoft.sstarter.domain.user.User;
import com.kiesoft.sstarter.dto.AbstractDTO;
import com.kiesoft.sstarter.dto.article.ArticleDTO;
import com.kiesoft.sstarter.dto.role.RoleDTO;

import java.util.HashSet;
import java.util.Set;

public class UserDTO extends AbstractDTO implements User<RoleDTO, ArticleDTO> {

    private String username;
    private String password;
    private Boolean enabled;
    private Set<RoleDTO> roles = new HashSet<>();
    private Set<ArticleDTO> articles = new HashSet<>();

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Boolean getEnabled() {
        return enabled;
    }

    @Override
    public Set<RoleDTO> getRoles() {
        return roles;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }

    @Override
    public Set<ArticleDTO> getArticles() {
        return articles;
    }

    @Override
    public void setArticles(Set<ArticleDTO> articles) {
        this.articles = articles;
    }

    @Override
    public void addArticle(ArticleDTO article) {
        article.setOwner(this);
        this.articles.add(article);
    }

    @Override
    public void removeArticle(ArticleDTO article) {
        article.setOwner(null);
        this.articles.remove(article);
    }

}
