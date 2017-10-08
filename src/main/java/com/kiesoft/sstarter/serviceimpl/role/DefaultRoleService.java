package com.kiesoft.sstarter.serviceimpl.role;

import com.kiesoft.sstarter.dto.role.RoleDTO;
import com.kiesoft.sstarter.exception.ElementNotFoundException;
import com.kiesoft.sstarter.exception.PersistenceProblemException;
import com.kiesoft.sstarter.jpa.entity.role.RoleEntity;
import com.kiesoft.sstarter.jpa.repository.RoleRepository;
import com.kiesoft.sstarter.service.role.RoleService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "roleService")
public class DefaultRoleService implements RoleService {

    private RoleRepository repository;
    private DozerBeanMapper dozerBeanMapper;
    private Environment env;

    @Autowired
    public DefaultRoleService(RoleRepository repository, DozerBeanMapper dozerBeanMapper, Environment env) {
        this.repository = repository;
        this.dozerBeanMapper = dozerBeanMapper;
        this.env = env;
    }

    @Override
    public void delete(RoleDTO roleDTO) {
        try {
            repository.delete(dozerBeanMapper.map(roleDTO, RoleEntity.class));
        } catch (Exception exception) {
            throw new PersistenceProblemException(exception);
        }
    }

    @Override
    public RoleDTO save(RoleDTO dto) {
        try {
            RoleEntity savedEntity = repository.save(dozerBeanMapper.map(dto, RoleEntity.class));
            return dozerBeanMapper.map(savedEntity, RoleDTO.class);
        } catch (Exception exception) {
            throw new PersistenceProblemException(exception);
        }
    }

    @Override
    public RoleDTO findOne(Long id) {
        RoleEntity entity = repository.findOne(id);
        if (entity == null) {
            throw new ElementNotFoundException(RoleEntity.class.toString(), id);
        }
        return dozerBeanMapper.map(entity, RoleDTO.class);
    }

    @Override
    public Page<RoleDTO> findAll(Pageable pageable) {
        List<RoleDTO> listDTO = new ArrayList<>();
        for (RoleEntity item : repository.findAll(pageable)) {
            listDTO.add(dozerBeanMapper.map(item, RoleDTO.class));
        }
        return new PageImpl<>(listDTO, pageable, repository.count());
    }

    @Override
    public List<RoleDTO> findAll() {
        List<RoleDTO> listDTO = new ArrayList<>();
        for (RoleEntity item : repository.findAll()) {
            listDTO.add(dozerBeanMapper.map(item, RoleDTO.class));
        }
        return listDTO;
    }

    @Override
    public RoleDTO getRoleAdmin() {
        return dozerBeanMapper.map(repository.findByRolename(env.getProperty("role.admin")), RoleDTO.class);
    }

    @Override
    public RoleDTO getRoleEditor() {
        return dozerBeanMapper.map(repository.findByRolename(env.getProperty("role.editor")), RoleDTO.class);
    }

}