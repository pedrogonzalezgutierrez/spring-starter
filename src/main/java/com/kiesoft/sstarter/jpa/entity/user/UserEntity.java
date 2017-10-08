package com.kiesoft.sstarter.jpa.entity.user;

import com.kiesoft.sstarter.domain.user.User;
import com.kiesoft.sstarter.jpa.entity.AbstractEntity;
import com.kiesoft.sstarter.jpa.entity.role.RoleEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sstarter_user", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class UserEntity extends AbstractEntity implements User<RoleEntity> {

    private String username;
    private String password;
    private Boolean enabled;
    private List<RoleEntity> roles = new ArrayList<>();

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "sstarter_user_roles",
            joinColumns = {@JoinColumn(name = "idUser")},
            inverseJoinColumns = {@JoinColumn(name = "idRole")})
    @Override
    public List<RoleEntity> getRoles() {
        return roles;
    }

    @Override
    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }

}
