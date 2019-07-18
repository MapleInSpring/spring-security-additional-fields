package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class TrialSecurityConfig extends WebSecurityConfigurerAdapter {

    private CustomerUserDetailsAuthenticationProvider authenticationProvider;

    public TrialSecurityConfig(CustomerUserDetailsAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(getFilter(), UsernamePasswordAuthenticationFilter.class)
                .csrf().disable()
                .authenticationProvider(authenticationProvider)
                .authorizeRequests()
                .mvcMatchers("/anyone-can-see").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic()
        ;
    }

    CustomerAuthenticationFilter getFilter() throws Exception {
        CustomerAuthenticationFilter filter = new CustomerAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }
}
