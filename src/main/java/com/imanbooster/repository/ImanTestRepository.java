package com.imanbooster.repository;

import com.imanbooster.domain.ImanTest;
import com.imanbooster.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImanTestRepository extends JpaRepository<ImanTest, Long> {
    List<ImanTest> findByUserOrderByDateTakenDesc(User user);
}
