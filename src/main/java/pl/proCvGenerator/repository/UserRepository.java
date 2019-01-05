package pl.proCvGenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.proCvGenerator.dao.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getUserByLogin(String login);
}
