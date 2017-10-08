package com.kiesoft.sstarter.service.role;

import com.kiesoft.sstarter.service.DeleteEntityService;
import com.kiesoft.sstarter.service.FindAllPaginationService;
import com.kiesoft.sstarter.service.FindAllService;
import com.kiesoft.sstarter.service.FindOneService;
import com.kiesoft.sstarter.service.SaveEntityService;
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
