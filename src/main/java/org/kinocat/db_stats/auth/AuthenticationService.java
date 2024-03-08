package org.kinocat.db_stats.auth;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.kinocat.db_stats.config.JwtService;
import org.kinocat.db_stats.entity.User;
import org.kinocat.db_stats.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final Logger log = LogManager.getLogger(this.getClass());

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(User.Role.USER);
        User registeredUser = userRepository.save(user);
        log.info("User: " + registeredUser + " was register");
        return generateResponse(user);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        User user = userRepository.findUserByEmail(request.getEmail())
                .orElseThrow();
        return generateResponse(user);
    }

    private AuthenticationResponse generateResponse(User user) {
        String token = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(token).build();
    }
}
