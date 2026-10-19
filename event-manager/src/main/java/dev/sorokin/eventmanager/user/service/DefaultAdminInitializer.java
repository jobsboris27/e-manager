package dev.sorokin.eventmanager.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultAdminInitializer {
    private final UserService userService;
    private final PasswordEncoder encoder;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        if (userService.existsByLogin("admin")) {
            return;
        }

        try {
            userService.createAdmin("admin",encoder.encode("admin"));
            log.info("Default admin created");
        } catch (IllegalArgumentException | DataIntegrityViolationException ex) {
            log.info("Default admin already exists, skipping: {}", ex.getMessage());
        }
    }
}