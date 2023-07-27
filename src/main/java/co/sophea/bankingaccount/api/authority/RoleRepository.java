package co.sophea.bankingaccount.api.authority;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role , Integer> {
}
