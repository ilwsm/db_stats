package org.kinocat.db_stats.repository;

import org.kinocat.db_stats.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findUserByEmail(String email);
}

