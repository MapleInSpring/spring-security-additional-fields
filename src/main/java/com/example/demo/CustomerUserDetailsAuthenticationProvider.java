package com.example.demo;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import static java.util.Collections.emptyList;

@Component
public class CustomerUserDetailsAuthenticationProvider implements AuthenticationProvider {

    private SimpleUserDetailsService userDetailsService;

    public CustomerUserDetailsAuthenticationProvider(SimpleUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String usernameCorpId = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();

        // can use a custom defined user detail service that returns the hard token id
        userDetailsService.loadUserByUsername(usernameCorpId);

        if ("password".equals(password)) {
            return new UsernamePasswordAuthenticationToken(usernameCorpId, password, emptyList());
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class).isAssignableFrom(authentication);
    }
}
