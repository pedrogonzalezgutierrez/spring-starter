package com.kiesoft.sstarter.jpa.entity;

import com.kiesoft.sstarter.domain.BaseEntity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class AbstractEntity implements BaseEntity {

    private Long id;

    @Id
    @GeneratedValue
    @Override
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
