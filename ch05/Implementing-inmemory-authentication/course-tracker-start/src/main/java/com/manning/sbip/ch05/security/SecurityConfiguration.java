package com.manning.sbip.ch05.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    AccessDeniedHandler accessDeniedHandler;

    protected void configure(HttpSecurity auth) throws Exception {
        auth.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/delete/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login")
                .and()
                .exceptionHandling().accessDeniedHandler(accessDeniedHandler);
    }

    @Override
    public void configure(WebSecurity webSecurity) throws  Exception {
        webSecurity.ignoring().antMatchers("/webjars/**", "/images/**", "/css/**", "/h2-console/**");
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.inMemoryAuthentication().passwordEncoder(passwordEncoder())
                .withUser("user")
                .password(passwordEncoder().encode("giorguna"))
                .roles("USER")
                .and()
                .withUser("admin")
                .password(passwordEncoder().encode("giorguna"))
                .roles("ADMIN");
    }

//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        UserDetails user = User.withUsername("user")
//                .passwordEncoder(passwordEncoder()::encode)
//                .password("user")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.withUsername("admin")
//                .passwordEncoder(passwordEncoder()::encode)
//                .password("admin")
//                .roles("ADMIN")
//                .build();
//
//        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(user);
//        manager.createUser(admin);
//
//        return manager;
//    }

    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


}
