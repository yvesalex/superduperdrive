package com.udacity.jwdnd.course1.cloudstorage.config;

import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(this.authenticationService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/signup","/css/**","/js/**").permitAll()
                .anyRequest().authenticated();

        http.formLogin().loginPage("/login").permitAll().failureUrl("/login?error").permitAll().and()
                .logout().invalidateHttpSession(true).clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout").permitAll();

        http.formLogin().defaultSuccessUrl("/home", true);
    }
}
