package com.ll.exam.sbb.repository;

import com.ll.exam.sbb.entity.SiteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<SiteUser, Integer> {
    boolean existsByUsername(String username);

    Optional<SiteUser> findByUsername(String username);
}
