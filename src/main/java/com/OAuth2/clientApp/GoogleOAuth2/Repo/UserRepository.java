package com.OAuth2.clientApp.GoogleOAuth2.Repo;

import com.OAuth2.clientApp.GoogleOAuth2.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
