package com.web.ecom_template.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import com.web.ecom_template.entity.VerificationToken;
import com.web.ecom_template.entity.LocalUser;

import java.util.List;
import java.util.Optional;

@Repository
public interface VerificationTokenRepository extends ListCrudRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
    void deleteByUser(LocalUser user);
    List<VerificationToken> findByUser_IdOrderByIdDesc(Long id);
}
