package com.soulware.tcompro.iam.domain.repositories;

import com.soulware.tcompro.iam.domain.model.aggregates.User;

import java.util.Optional;

public interface UserRepository {
    void save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
}
