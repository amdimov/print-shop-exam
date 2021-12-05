package com.example.transferhall.service.Impl;

import com.example.transferhall.models.SecurityUser;
import com.example.transferhall.models.UsersEntity;
import com.example.transferhall.models.enums.UserRoleEnum;
import com.example.transferhall.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceSecurity implements UserDetailsService {
    private final UserRepository userRepository;

    public UserServiceSecurity(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UsersEntity usersEntity = userRepository.findUsersEntityByEmail(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("No user with the provided email"));
        List<GrantedAuthority> authorities =
               usersEntity.getRoles().stream()
                        .map(e -> new SimpleGrantedAuthority("ROLE_"+e.getRole()))
                        .collect(Collectors.toList());
        SecurityUser securityUser = new SecurityUser(usersEntity.getEmail(), usersEntity.getPassword()
                , authorities);
        securityUser.setCompanyName(usersEntity.getCompanyName());
        return securityUser;
    }
}
