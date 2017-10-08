package com.kiesoft.sstarter.login;

import com.kiesoft.sstarter.auth.DefaultUserDetails;
import com.kiesoft.sstarter.jpa.entity.user.UserEntity;
import com.kiesoft.sstarter.jpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.transaction.Transactional;
import java.util.Objects;

@Transactional
public class LoginService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = userRepository.findByUsername(username);
        if (Objects.isNull(userEntity)) {
            throw new UsernameNotFoundException("Username not found: " + username);
        }

        UserDetails userDetails = new DefaultUserDetails(
                userEntity.getEnabled(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getRoles()
        );
        return userDetails;
    }


}
