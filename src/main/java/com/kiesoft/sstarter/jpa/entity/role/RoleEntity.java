package com.kiesoft.sstarter.jpa.entity.role;

import com.kiesoft.sstarter.domain.role.Role;
import com.kiesoft.sstarter.jpa.entity.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "sstarter_role", uniqueConstraints = {@UniqueConstraint(columnNames = {"rolename"})})
public class RoleEntity extends AbstractEntity implements Role {

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