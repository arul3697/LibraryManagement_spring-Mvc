package com.ssbsoft.library.config;


import com.ssbsoft.library.model.User;
import com.ssbsoft.library.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;


@Component
public class
CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping (value = "/session")
    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        System.out.println("----Authentication Provider------");
        String name = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        User user = new User();
        user.setName(name);
        User userDetails;
        try {
            userDetails = this.userService.getUserByUsername(user);
            if (StringUtils.isEmpty(password)) {
                throw new BadCredentialsException("No Username and/or Password Provided.");
            } else if (!userDetails.getPassword().equals(password)) {
                throw new BadCredentialsException("Invalid Username or Password");
            } else {
                return new UsernamePasswordAuthenticationToken(
                        userDetails, password, new ArrayList<>());
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new BadCredentialsException(e.getMessage());
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
