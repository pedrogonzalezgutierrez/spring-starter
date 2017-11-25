package com.kiesoft.sstarter.jpa.repository;

import com.kiesoft.sstarter.jpa.entity.user.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long> {

    @EntityGraph(value = "findOne", type = EntityGraph.EntityGraphType.FETCH)
    @Override
    UserEntity findOne(Long id);

    @EntityGraph(value = "findOne", type = EntityGraph.EntityGraphType.FETCH)
    UserEntity findByUsername(String username);

    @EntityGraph(value = "findOne", type = EntityGraph.EntityGraphType.FETCH)
    @Override
    Page<UserEntity> findAll(Pageable pageable);
}
