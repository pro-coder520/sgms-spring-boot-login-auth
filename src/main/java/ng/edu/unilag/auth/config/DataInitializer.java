package ng.edu.unilag.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import ng.edu.unilag.auth.model.ERole;
import ng.edu.unilag.auth.model.Role;
import ng.edu.unilag.auth.model.User;
import ng.edu.unilag.auth.repository.RoleRepository;
import ng.edu.unilag.auth.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class DataInitializer {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            // Initialize roles if they don't exist
            if (roleRepository.count() == 0) {
                Role adminRole = new Role(ERole.ROLE_ADMIN);
                Role teacherRole = new Role(ERole.ROLE_TEACHER);
                Role studentRole = new Role(ERole.ROLE_STUDENT);

                roleRepository.save(adminRole);
                roleRepository.save(teacherRole);
                roleRepository.save(studentRole);

                // Create sample users

                // Admin
                User admin = new User();
                admin.setUsername("admin");
                admin.setEmail("admin@example.com");
                admin.setFullName("Admin User");
                admin.setPassword(passwordEncoder.encode("admin123"));
                Set<Role> adminRoles = new HashSet<>();
                adminRoles.add(adminRole);
                admin.setRoles(adminRoles);
                userRepository.save(admin);

                // Teacher
                User teacher = new User();
                teacher.setUsername("teacher");
                teacher.setEmail("teacher@example.com");
                teacher.setFullName("Teacher User");
                teacher.setPassword(passwordEncoder.encode("teacher123"));
                Set<Role> teacherRoles = new HashSet<>();
                teacherRoles.add(teacherRole);
                teacher.setRoles(teacherRoles);
                userRepository.save(teacher);

                // Student
                User student = new User();
                student.setUsername("student");
                student.setEmail("student@example.com");
                student.setFullName("Student User");
                student.setPassword(passwordEncoder.encode("student123"));
                Set<Role> studentRoles = new HashSet<>();
                studentRoles.add(studentRole);
                student.setRoles(studentRoles);
                userRepository.save(student);
            }
        };
    }
}
