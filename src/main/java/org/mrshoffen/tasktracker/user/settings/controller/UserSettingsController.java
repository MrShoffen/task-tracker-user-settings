package org.mrshoffen.tasktracker.user.settings.controller;

import lombok.RequiredArgsConstructor;
import org.mrshoffen.tasktracker.user.settings.model.dto.UserSettingsDto;
import org.mrshoffen.tasktracker.user.settings.service.UserSettingsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.mrshoffen.tasktracker.commons.web.authentication.AuthenticationAttributes.AUTHORIZED_USER_HEADER_NAME;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/me/settings")
public class UserSettingsController {

    private final UserSettingsService userSettingsService;

    @GetMapping
    ResponseEntity<UserSettingsDto> getUserSettings(@RequestHeader(AUTHORIZED_USER_HEADER_NAME) String userId) {

        return ResponseEntity.ok(userSettingsService.getUserSettings(UUID.fromString(userId)));
    }
}
