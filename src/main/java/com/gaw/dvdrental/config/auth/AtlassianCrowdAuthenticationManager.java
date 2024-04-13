package com.gaw.dvdrental.config.auth;

import com.gaw.dvdrental.model.entity.Role;
import com.gaw.dvdrental.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class AtlassianCrowdAuthenticationManager implements AuthenticationManager {

    private UserRepository userRepository;

    public AtlassianCrowdAuthenticationManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("### AtlassianCrowdAuthenticationProvider authenticate");
        String username = authentication.getPrincipal().toString();
        String password = authentication.getCredentials().toString();
        System.out.println(username + " | " + password);

        User user = callAtlassianCrowdRestService(username, password);
        if (user == null) {
            throw new UsernameNotFoundException("could not login! invalid password oke");
        }
        return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities()); // (4)
    }

    private User callAtlassianCrowdRestService(String username, String password) {
        if ("123".equals(password)){
            return null;
        }
        System.out.printf("### callAtlassianCrowdRestService(%s)%n", username);
        com.gaw.dvdrental.model.entity.User user = userRepository.findByEmail(username);
        user.getRoles().forEach( role -> {
            System.out.println(role.getId() + " # " + role.getName());
        });
        return new org.springframework.security.core.userdetails.User(username, password, mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map( role ->
                new SimpleGrantedAuthority(role.getName())
        ).collect(Collectors.toList());
    }

}
