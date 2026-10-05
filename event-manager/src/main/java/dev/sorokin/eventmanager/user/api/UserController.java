package dev.sorokin.eventmanager.user.api;

import dev.sorokin.eventmanager.security.auth.AuthenticationService;
import dev.sorokin.eventmanager.security.dto.JwtResponse;
import dev.sorokin.eventmanager.user.api.dto.SignInRequest;
import dev.sorokin.eventmanager.user.api.dto.SignUpRequest;
import dev.sorokin.eventmanager.user.api.dto.UserResponse;
import dev.sorokin.eventmanager.user.mapper.UserMapper;
import dev.sorokin.eventmanager.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationService authenticationService;
    private final UserMapper mapper;

    @PostMapping
    public ResponseEntity<UserResponse> register(
            @RequestBody @Valid SignUpRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(mapper.toDto(userService.register(request)));
    }

    @PostMapping("/auth")
    public JwtResponse authenticate(@RequestBody @Valid SignInRequest request) {
        return new JwtResponse(authenticationService.authenticate(request));
    }

    @GetMapping("/{userId}")
    public UserResponse getById(@PathVariable Long userId) {
        return mapper.toDto(userService.getById(userId));
    }
}
