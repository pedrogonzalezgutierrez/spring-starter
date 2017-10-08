package com.kiesoft.sstarter.auth;

import com.kiesoft.sstarter.domain.role.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DefaultUserDetails implements UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 2097815953506615768L;

    private boolean enabled;
    private String username;
    private String password;
    private List<GrantedAuthority> listGrantedAuthority;

    public DefaultUserDetails(boolean enabled, String username, String password, List<? extends Role> roles) {
        super();
        this.enabled = enabled;
        this.username = username;
        this.password = password;

        List<GrantedAuthority> listGrantedAuthority = new ArrayList<>();
        roles.forEach(role -> listGrantedAuthority.add(new DefaultGrantedAuthority(role.getRolename())));
        this.listGrantedAuthority = listGrantedAuthority;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return listGrantedAuthority;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

}
