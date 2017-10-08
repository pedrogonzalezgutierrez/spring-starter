package com.kiesoft.sstarter.dto.user;

import com.kiesoft.sstarter.domain.user.User;
import com.kiesoft.sstarter.dto.AbstractDTO;
import com.kiesoft.sstarter.dto.role.RoleDTO;

import java.util.ArrayList;
import java.util.List;

public class UserDTO extends AbstractDTO implements User<RoleDTO> {

    private String username;
    private String password;
    private Boolean enabled;
    private List<RoleDTO> roles = new ArrayList<>();

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
    public List<RoleDTO> getRoles() {
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
    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

}
