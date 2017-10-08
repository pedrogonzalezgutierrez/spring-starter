package com.kiesoft.sstarter.domain.role;

import com.kiesoft.sstarter.domain.BaseEntity;

public interface Role extends BaseEntity {

    String getRolename();

    void setRolename(String rolename);
}