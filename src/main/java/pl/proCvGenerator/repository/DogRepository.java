package pl.proCvGenerator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.proCvGenerator.dao.Dog;

public interface DogRepository extends JpaRepository<Dog, Long> {
}
