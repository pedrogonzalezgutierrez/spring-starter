package com.kiesoft.sstarter.jpa.repository;

import com.kiesoft.sstarter.jpa.entity.language.LanguageEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LanguageRepository extends PagingAndSortingRepository<LanguageEntity, Long> {
    
    LanguageEntity findByCode(String code);

}
