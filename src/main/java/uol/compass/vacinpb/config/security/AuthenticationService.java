package uol.compass.vacinpb.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import uol.compass.vacinpb.entity.Employee;
import uol.compass.vacinpb.repository.EmployeeRepository;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> userRole = employeeRepository.findByEmail(username);
        if (userRole.isPresent()) {
            return (UserDetails) userRole.get();
        }
        throw new UsernameNotFoundException("Dados inválidos");
    }
}
