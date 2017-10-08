package com.kiesoft.sstarter.jpa.repository;

import com.kiesoft.sstarter.jpa.entity.user.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

    UserEntity findByUsername(String username);

}
