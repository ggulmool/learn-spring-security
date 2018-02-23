package me.ggulmool.lss.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import me.ggulmool.lss.persistence.model.Privilege;

public interface PrivilegeRepository extends JpaRepository<Privilege, Long> {

    public Privilege findByName(String name);

}
