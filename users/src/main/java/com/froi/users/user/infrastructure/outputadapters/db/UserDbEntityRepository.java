package com.froi.users.user.infrastructure.outputadapters.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDbEntityRepository extends JpaRepository<UserDbEntity, String> {
    Optional<UserDbEntity> findByEmail(String email);
}
