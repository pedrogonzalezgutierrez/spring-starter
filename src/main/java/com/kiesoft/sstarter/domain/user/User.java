package com.kiesoft.sstarter.domain.user;

import com.kiesoft.sstarter.domain.BaseEntity;

import java.util.List;

public interface User<T> extends BaseEntity {

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    Boolean getEnabled();

    void setEnabled(Boolean enabled);

    List<T> getRoles();

    void setRoles(List<T> roles);

}
