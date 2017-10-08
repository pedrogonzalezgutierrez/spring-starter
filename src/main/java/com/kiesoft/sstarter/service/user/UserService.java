package com.kiesoft.sstarter.service.user;

import com.kiesoft.sstarter.service.DeleteEntityService;
import com.kiesoft.sstarter.service.FindAllPaginationService;
import com.kiesoft.sstarter.service.FindOneService;
import com.kiesoft.sstarter.service.SaveEntityService;
import com.kiesoft.sstarter.dto.user.UserDTO;

public interface UserService extends
        SaveEntityService<UserDTO>,
        DeleteEntityService<UserDTO>,
        FindOneService<UserDTO>,
        FindAllPaginationService<UserDTO> {

    UserDTO findByUsername(String username);


}
