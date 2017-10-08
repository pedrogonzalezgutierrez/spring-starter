package com.kiesoft.sstarter.serviceimpl.user;

import com.kiesoft.sstarter.dto.user.UserDTO;
import com.kiesoft.sstarter.exception.ElementNotFoundException;
import com.kiesoft.sstarter.exception.PersistenceProblemException;
import com.kiesoft.sstarter.jpa.entity.user.UserEntity;
import com.kiesoft.sstarter.jpa.repository.UserRepository;
import com.kiesoft.sstarter.service.user.UserService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service(value = "userService")
public class DefaultUserService implements UserService {

    private UserRepository repository;
    private DozerBeanMapper dozerBeanMapper;

    @Autowired
    public DefaultUserService(UserRepository repository, DozerBeanMapper dozerBeanMapper) {
        this.repository = repository;
        this.dozerBeanMapper = dozerBeanMapper;
    }

    @Override
    public UserDTO save(UserDTO dto) {
        try {
            UserEntity savedEntity = repository.save(dozerBeanMapper.map(dto, UserEntity.class));
            return dozerBeanMapper.map(savedEntity, UserDTO.class);
        } catch (Exception exception) {
            throw new PersistenceProblemException(exception);
        }
    }

    @Override
    public UserDTO findOne(Long id) {
        UserEntity entity = repository.findOne(id);
        if (entity == null) {
            throw new ElementNotFoundException(UserEntity.class.toString(), id);
        }
        return dozerBeanMapper.map(entity, UserDTO.class);
    }

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        List<UserDTO> listDTO = new ArrayList<>();
        for (UserEntity item : repository.findAll(pageable)) {
            listDTO.add(dozerBeanMapper.map(item, UserDTO.class));
        }
        return new PageImpl<>(listDTO, pageable, repository.count());
    }

    @Override
    public void delete(UserDTO userDTO) throws PersistenceProblemException {
        try {
            repository.delete(dozerBeanMapper.map(userDTO, UserEntity.class));
        } catch (Exception exception) {
            throw new PersistenceProblemException(exception);
        }
    }

    @Override
    public UserDTO findByUsername(String username) {
        return dozerBeanMapper.map(repository.findByUsername(username), UserDTO.class);
    }
}
