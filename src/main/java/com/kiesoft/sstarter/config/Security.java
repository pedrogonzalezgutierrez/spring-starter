package com.kiesoft.sstarter.config;

import com.kiesoft.sstarter.login.LoginService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static com.kiesoft.sstarter.controller.rest.user.AbstractRestUserController.ROUTING_REST_USER_CONTROLLER;
import static com.kiesoft.sstarter.controller.mvc.language.AbstractLanguageController.ROUTING_LANGUAGE_CONTROLLER;
import static com.kiesoft.sstarter.controller.mvc.login.AbstractLoginController.*;
import static com.kiesoft.sstarter.controller.mvc.profile.AbstractUserProfileController.ROUTING_USERPROFILE_CONTROLLER;
import static com.kiesoft.sstarter.controller.mvc.role.AbstractRoleController.ROUTING_ROLE_CONTROLLER;
import static com.kiesoft.sstarter.controller.mvc.user.AbstractUserController.ROUTING_USER_CONTROLLER;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(ROUTING_USER_CONTROLLER + "/**").hasRole("ADMIN")
                .antMatchers(ROUTING_ROLE_CONTROLLER + "/**").hasRole("ADMIN")
                .antMatchers(ROUTING_LANGUAGE_CONTROLLER + "/**").hasAnyRole("ADMIN", "EDITOR")

                .antMatchers(ROUTING_USERPROFILE_CONTROLLER + "/**").authenticated()

                .and().csrf().disable()
                .authorizeRequests()
                .antMatchers(ROUTING_REST_USER_CONTROLLER + "/**").permitAll()

                .and()
                .formLogin()
                .loginProcessingUrl(ROUTING_LOGIN)
                .loginPage(ROUTING_LOGIN_PAGE)
                .defaultSuccessUrl("/")
                .failureUrl(ROUTING_LOGIN_FAILURE)
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()

                .and()
                .logout()
                .logoutUrl(ROUTING_LOGOUT)
                .logoutSuccessUrl("/")
                .permitAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginService()).passwordEncoder(md5PasswordEncoder());
        //auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
    }

    @Bean
    public LoginService loginService() {
        LoginService loginService = new LoginService();
        return loginService;
    }

    @Bean
    public Md5PasswordEncoder md5PasswordEncoder() {
        return new Md5PasswordEncoder();
    }

}
