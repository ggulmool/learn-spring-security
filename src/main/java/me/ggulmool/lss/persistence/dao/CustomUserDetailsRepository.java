package me.ggulmool.lss.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import me.ggulmool.lss.persistence.model.CustomUserDetails;

public interface CustomUserDetailsRepository extends JpaRepository<CustomUserDetails, Long> {

    public CustomUserDetails findByUsername(String username);

}
