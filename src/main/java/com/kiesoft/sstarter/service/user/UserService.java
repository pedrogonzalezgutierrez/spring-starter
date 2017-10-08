package com.kiesoft.sstarter.service.user;

import com.kiesoft.sstarter.crud.DeleteEntityService;
import com.kiesoft.sstarter.crud.FindAllPaginationService;
import com.kiesoft.sstarter.crud.FindOneService;
import com.kiesoft.sstarter.crud.SaveEntityService;
import com.kiesoft.sstarter.dto.user.UserDTO;

public interface UserService extends
        SaveEntityService<UserDTO>,
        DeleteEntityService<UserDTO>,
        FindOneService<UserDTO>,
        FindAllPaginationService<UserDTO> {

    UserDTO findByUsername(String username);


}
