package com.example.transferhall.repository;

import com.example.transferhall.models.GalleryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GalleryRepository extends JpaRepository<GalleryEntity, Long> {
    Optional<GalleryEntity> findByPublicId(String publicId);
    void deleteGalleryEntitiesByPublicId(String publicId);
}
