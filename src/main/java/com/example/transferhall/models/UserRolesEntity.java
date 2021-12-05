package com.example.transferhall.models;

import com.example.transferhall.models.enums.UserRoleEnum;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
public class UserRolesEntity extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    public UserRoleEnum getRole() {
        return role;
    }

    public UserRolesEntity setRole(UserRoleEnum roles) {
        this.role = roles;
        return this;
    }
}
