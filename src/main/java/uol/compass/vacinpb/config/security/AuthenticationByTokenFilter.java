package uol.compass.vacinpb.config.security;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import uol.compass.vacinpb.entity.Employee;
import uol.compass.vacinpb.repository.EmployeeRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationByTokenFilter extends OncePerRequestFilter {

    private TokenService tokenService;
    private EmployeeRepository employeeRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = recoverToken(request);
        boolean valid = tokenService.isTokenValid(token);
        if (valid) {
            authenticateClient(token);
        }

        filterChain.doFilter(request, response);
    }

    private  void authenticateClient(String token) {
        Long idEmployee = tokenService.getIdEmployee(token);
        Employee employee = employeeRepository.findById(idEmployee).get();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(employee, null, employee.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    private String recoverToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }

        return token.substring(7, token.length());
    }
}
