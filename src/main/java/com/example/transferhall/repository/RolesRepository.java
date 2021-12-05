package com.example.transferhall.repository;

import com.example.transferhall.models.UserRolesEntity;
import com.example.transferhall.models.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<UserRolesEntity, Long> {
  UserRolesEntity getUserRolesEntitiesByRole(UserRoleEnum roles);
}
