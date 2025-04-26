package org.mrshoffen.tasktracker.user.settings.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mrshoffen.tasktracker.user.settings.exception.UserNotFoundException;
import org.mrshoffen.tasktracker.user.settings.model.dto.UserSettingsDto;
import org.mrshoffen.tasktracker.user.settings.model.entity.UserSettings;
import org.mrshoffen.tasktracker.user.settings.repository.UserSettingsRepository;
import org.mrshoffen.tasktracker.user.settings.util.TimeZoneResolver;
import org.mrshoffen.tasktracker.user.settings.util.mapper.UserSettingsMapper;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserSettingsService {

    private final TimeZoneResolver timeZoneResolver;

    private final UserSettingsRepository userSettingsRepository;

    private final UserSettingsMapper userSettingsMapper;

    public UserSettingsDto createDefaultSettingsForUser(UUID userId, String userIp) {
        String userTimeZone = timeZoneResolver.resolveTimeZoneFromIp(userIp);

        UserSettings userSettings = UserSettings.builder()
                .id(userId)
                .dailyNotificationsEnabled(true)
                .dailyNotificationTime(LocalTime.MIDNIGHT)
                .timeZone(userTimeZone)
                .build();
        userSettingsRepository.save(userSettings);
        log.info("Saved default settings for user with id {}", userId.toString());

        return userSettingsMapper.toDto(userSettings);
    }

    public UserSettingsDto getUserSettings(UUID userId) {
        return userSettingsRepository.findById(userId)
                .map(userSettingsMapper::toDto)
                .orElseGet(() -> createDefaultSettingsForUser(userId, "0.0.0.0"));
    }

}
