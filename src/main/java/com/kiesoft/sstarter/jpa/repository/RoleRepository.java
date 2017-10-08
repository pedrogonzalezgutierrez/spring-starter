package com.kiesoft.sstarter.jpa.repository;

import com.kiesoft.sstarter.jpa.entity.role.RoleEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleRepository extends PagingAndSortingRepository<RoleEntity, Long> {

    RoleEntity findByRolename(String rolename);

}
