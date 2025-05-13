package com.web.ecom_template.repository;

import org.springframework.data.repository.ListCrudRepository;
import com.web.ecom_template.entity.LocalUser;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LocalUserRepository extends ListCrudRepository<LocalUser, Long> {
    Optional<LocalUser> findByUsernameIgnoreCase(String username);
    Optional<LocalUser> findByEmailIgnoreCase(String email);
}
