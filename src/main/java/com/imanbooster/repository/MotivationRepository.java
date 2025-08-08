package com.imanbooster.repository;

import com.imanbooster.domain.Motivation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MotivationRepository extends JpaRepository<Motivation, Long> {
    @Query("SELECT m FROM Motivation m WHERE m.range = :range ORDER BY RAND() LIMIT 1")
    Motivation getRandomMotivationByRange(@Param("range") int range);
}