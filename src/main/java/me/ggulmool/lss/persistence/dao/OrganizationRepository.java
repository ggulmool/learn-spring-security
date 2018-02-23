package me.ggulmool.lss.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import me.ggulmool.lss.persistence.model.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    public Organization findByName(String name);

}

