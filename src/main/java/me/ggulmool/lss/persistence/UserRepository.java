package me.ggulmool.lss.persistence;

import me.ggulmool.lss.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
