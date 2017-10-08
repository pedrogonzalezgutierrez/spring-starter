package com.kiesoft.sstarter.dto.role;

import com.kiesoft.sstarter.domain.role.Role;
import com.kiesoft.sstarter.dto.AbstractDTO;

public class RoleDTO extends AbstractDTO implements Role {

    private String rolename;

    @Override
    public String getRolename() {
        return rolename;
    }

    @Override
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

}
