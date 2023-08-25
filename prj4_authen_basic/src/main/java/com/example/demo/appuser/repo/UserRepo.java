package com.example.demo.appuser.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.appuser.AppUser;


@Repository
@Transactional(readOnly = true)
public interface UserRepo extends JpaRepository<AppUser,Long> {

    @Query("SELECT s FROM AppUser s where s.email = ?1")
    Optional<AppUser> findyByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.enable = TRUE WHERE a.email = ?1")
    int enableAppUser(String email);
    
}
