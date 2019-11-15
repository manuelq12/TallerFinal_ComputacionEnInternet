package ci.workshop.test.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ci.workshop.test.model.User;

public interface UserRepository extends CrudRepository<User, String> {

	Optional<User> findByUsername(String username);

}
