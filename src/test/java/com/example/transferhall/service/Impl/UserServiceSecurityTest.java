package com.example.transferhall.service.Impl;

import com.example.transferhall.models.UserRolesEntity;
import com.example.transferhall.models.UsersEntity;
import com.example.transferhall.models.enums.UserRoleEnum;
import com.example.transferhall.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.management.relation.Role;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
class UserServiceSecurityTest {
    private UsersEntity testUser;
    private UserRolesEntity adminRole, userRole;
    private UserServiceSecurity userSecurityTest;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void init(){
        this.userSecurityTest = new UserServiceSecurity(userRepository);

        adminRole = new UserRolesEntity().setRole(UserRoleEnum.ADMIN);
        userRole = new UserRolesEntity().setRole(UserRoleEnum.USER);

        this.testUser = new UsersEntity()
                .setFirstName("Alex")
                .setLastName("Dimov")
                .setEmail("admin@admin.com")
                .setPassword("password")
                .setCompanyName("Megaprint Transfers")
                .setRoles(Set.of(adminRole, userRole));
    }

    @Test
    void testUserNotFound(){
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                ()->userSecurityTest.loadUserByUsername("error@error.com"),
                "No user with the provided email"
        );
    }

    @Test
    void testUserFound(){
        Mockito.when(userRepository.findUsersEntityByEmail(testUser.getEmail()))
                .thenReturn(Optional.of(this.testUser));
        UserDetails actual = this.userSecurityTest.loadUserByUsername(this.testUser.getEmail());

        String expectedRoles = "ROLE_ADMIN, ROLE_USER";
        String actualRoles = actual.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));
        Assertions.assertEquals(actual.getUsername(), this.testUser.getEmail());
        Assertions.assertEquals(expectedRoles, actualRoles);

    }


}