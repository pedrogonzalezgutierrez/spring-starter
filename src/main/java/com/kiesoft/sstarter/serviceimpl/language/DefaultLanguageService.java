package com.kiesoft.sstarter.serviceimpl.language;

import com.kiesoft.sstarter.dto.language.LanguageDTO;
import com.kiesoft.sstarter.exception.ElementNotFoundException;
import com.kiesoft.sstarter.exception.PersistenceProblemException;
import com.kiesoft.sstarter.jpa.entity.language.LanguageEntity;
import com.kiesoft.sstarter.jpa.repository.LanguageRepository;
import com.kiesoft.sstarter.service.language.LanguageService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "languageService")
public class DefaultLanguageService implements LanguageService {

    private LanguageRepository repository;
    private DozerBeanMapper dozerBeanMapper;

    @Autowired
    public DefaultLanguageService(LanguageRepository repository, DozerBeanMapper dozerBeanMapper) {
        this.repository = repository;
        this.dozerBeanMapper = dozerBeanMapper;
    }

    @Override
    public LanguageDTO save(LanguageDTO dto) {
        try {
            LanguageEntity savedEntity = repository.save(dozerBeanMapper.map(dto, LanguageEntity.class));
            return dozerBeanMapper.map(savedEntity, LanguageDTO.class);
        } catch (Exception exception) {
            throw new PersistenceProblemException(exception);
        }
    }

    @Override
    public LanguageDTO findOne(Long id) {
        LanguageEntity entity = repository.findOne(id);
        if (entity == null) {
            throw new ElementNotFoundException(LanguageEntity.class.toString(), id);
        }
        return dozerBeanMapper.map(entity, LanguageDTO.class);
    }

    @Override
    public Page<LanguageDTO> findAll(Pageable pageable) {
        List<LanguageDTO> listDTO = new ArrayList<>();
        for (LanguageEntity item : repository.findAll(pageable)) {
            listDTO.add(dozerBeanMapper.map(item, LanguageDTO.class));
        }
        return new PageImpl<>(listDTO, pageable, repository.count());
    }

    @Override
    public List<LanguageDTO> findAll() {
        List<LanguageDTO> ret = new ArrayList<>();
        for (LanguageEntity item : repository.findAll()) {
            ret.add(dozerBeanMapper.map(item, LanguageDTO.class));
        }
        return ret;
    }

    @Override
    public LanguageDTO findByCode(String code) {
        return code == null ? null : dozerBeanMapper.map(repository.findByCode(code), LanguageDTO.class);
    }

    @Override
    public void delete(LanguageDTO languageDTO) {
        try {
            repository.delete(dozerBeanMapper.map(languageDTO, LanguageEntity.class));
        } catch (Exception exception) {
            throw new PersistenceProblemException(exception);
        }
    }
}
