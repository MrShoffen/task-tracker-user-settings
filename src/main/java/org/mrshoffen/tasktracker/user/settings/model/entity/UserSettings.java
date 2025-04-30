package org.mrshoffen.tasktracker.user.settings.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user_settings")
public class UserSettings {

    @Id
    @Column(name = "user_id", nullable = false)
    private UUID id;

    @ColumnDefault("true")
    @Column(name = "daily_notifications_enabled")
    private Boolean dailyNotificationsEnabled;

    @Column(name = "daily_notification_time", nullable = false)
    private LocalTime dailyNotificationTime;

    @Column(name = "time_zone", nullable = false)
    private String timeZone;

    public static UserSettings defaultUserSettings(UUID id, String timeZone) {
        return UserSettings.builder()
                .id(id)
                .dailyNotificationsEnabled(true)
                .dailyNotificationTime(LocalTime.MIDNIGHT)
                .timeZone(timeZone)
                .build();
    }
}
