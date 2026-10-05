package dev.sorokin.eventmanager.security.auth;


import dev.sorokin.eventmanager.security.jwt.JwtService;
import dev.sorokin.eventmanager.user.api.dto.SignInRequest;
import dev.sorokin.eventmanager.user.service.UserService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthenticationService(UserService userService,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public String authenticate(SignInRequest request) {
        var user = userService.findByLogin(request.login())
                .filter(u -> passwordEncoder.matches(
                        request.password(), u.passwordHash()))
                .orElseThrow(() -> new BadCredentialsException(
                        "Bad credentials"));
        return jwtService.generateToken(user);
    }
}