package ng.edu.unilag.auth.repository;

import ng.edu.unilag.auth.model.ERole;
import ng.edu.unilag.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
