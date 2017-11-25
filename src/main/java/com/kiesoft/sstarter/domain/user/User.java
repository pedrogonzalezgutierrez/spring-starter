package com.kiesoft.sstarter.domain.user;

import com.kiesoft.sstarter.domain.BaseEntity;

import java.util.List;
import java.util.Set;

public interface User<T,U> extends BaseEntity {

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    Boolean getEnabled();

    void setEnabled(Boolean enabled);

    Set<T> getRoles();

    void setRoles(Set<T> roles);

    Set<U> getArticles();

    void setArticles(Set<U> articles);

    void addArticle(U article);

    void removeArticle(U article);

}
