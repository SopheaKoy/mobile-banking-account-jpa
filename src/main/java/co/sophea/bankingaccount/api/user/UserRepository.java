package co.sophea.bankingaccount.api.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByEmailIAndIsDeletedFalse(String email);
}
