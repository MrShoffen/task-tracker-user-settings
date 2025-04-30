package org.mrshoffen.tasktracker.user.settings.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mrshoffen.tasktracker.commons.kafka.event.registration.RegistrationAttemptEvent;
import org.mrshoffen.tasktracker.commons.kafka.event.registration.RegistrationSuccessfulEvent;
import org.mrshoffen.tasktracker.user.settings.exception.UserNotFoundException;
import org.mrshoffen.tasktracker.user.settings.model.dto.UserSettingsDto;
import org.mrshoffen.tasktracker.user.settings.model.entity.UserSettings;
import org.mrshoffen.tasktracker.user.settings.repository.UserSettingsRepository;
import org.mrshoffen.tasktracker.user.settings.util.mapper.UserSettingsMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserSettingsService {

    private final UserSettingsRepository userSettingsRepository;

    private final UserSettingsMapper userSettingsMapper;

    public UserSettingsDto createDefaultSettingsForUser(RegistrationAttemptEvent event) {
        UserSettings userSettings = UserSettings.defaultUserSettings(event.getRegistrationId(), event.getTimeZone());

        userSettingsRepository.save(userSettings);
        log.info("Saved default settings for user with id {}", event.getRegistrationId().toString());

        return userSettingsMapper.toDto(userSettings);
    }

    public UserSettingsDto getUserSettings(UUID userId) {
        return userSettingsRepository.findById(userId)
                .map(userSettingsMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));
    }

    @Transactional
    public void deleteUserById(UUID userId) {
        userSettingsRepository.deleteById(userId);
        log.info("User settings deleted: {}", userId);

    }
}
