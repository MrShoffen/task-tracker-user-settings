package org.mrshoffen.tasktracker.user.settings.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public final class UserSettingsDto {
    private Boolean dailyNotificationsEnabled;
    private LocalTime dailyNotificationTime;
    private String timeZone;


}