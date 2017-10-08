package com.kiesoft.sstarter.service.role;

import com.kiesoft.sstarter.crud.DeleteEntityService;
import com.kiesoft.sstarter.crud.FindAllPaginationService;
import com.kiesoft.sstarter.crud.FindAllService;
import com.kiesoft.sstarter.crud.FindOneService;
import com.kiesoft.sstarter.crud.SaveEntityService;
import com.kiesoft.sstarter.dto.role.RoleDTO;

public interface RoleService extends
        SaveEntityService<RoleDTO>,
        DeleteEntityService<RoleDTO>,
        FindOneService<RoleDTO>,
        FindAllService<RoleDTO>,
        FindAllPaginationService<RoleDTO> {

    RoleDTO getRoleAdmin();

    RoleDTO getRoleEditor();

}
