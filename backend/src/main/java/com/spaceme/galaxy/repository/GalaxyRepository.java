package com.spaceme.galaxy.repository;

import com.spaceme.galaxy.domain.Galaxy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GalaxyRepository extends JpaRepository<Galaxy, Long> {
    List<Galaxy> findAllByUserId(Long userId);
    boolean existsByIdAndUserId(Long galaxyId, Long userId);
}