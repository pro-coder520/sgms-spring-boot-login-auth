package ng.edu.unilag.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import ng.edu.unilag.auth.model.ERole;
import ng.edu.unilag.auth.model.Role;
import ng.edu.unilag.auth.model.User;
import ng.edu.unilag.auth.repository.RoleRepository;
import ng.edu.unilag.auth.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class DbInit implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Initialize roles if they don't exist
        if (roleRepository.count() == 0) {
            roleRepository.save(new Role(ERole.ROLE_ADMIN));
            roleRepository.save(new Role(ERole.ROLE_TEACHER));
            roleRepository.save(new Role(ERole.ROLE_STUDENT));

            // Create an admin user
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setEmail("admin@example.com");
            adminUser.setFullName("Administrator");
            adminUser.setPassword(passwordEncoder.encode("admin123"));

            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName(ERole.ROLE_ADMIN).get());
            adminUser.setRoles(roles);
 
            userRepository.save(adminUser);
        }
    }
}
