package kz.axelrodadil.bookstore_samgau.repository;

import kz.axelrodadil.bookstore_samgau.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleEmail(String email);
}
