package com.furniturecloud.security_models;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AppUserRepo extends JpaRepository<ApplicationUser, Integer> {
	Optional<ApplicationUser>  findByUsername(String username);
}
