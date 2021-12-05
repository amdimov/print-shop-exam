package com.example.transferhall.googleDrive;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriveRepository extends JpaRepository<DriveEntity, Long> {
    void deleteAllByPublicId(String publicId);
}
