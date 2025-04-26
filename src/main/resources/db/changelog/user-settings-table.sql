--liquibase formatted sql


--changeset mrshoffen:2
CREATE TABLE IF NOT EXISTS user_settings
(
    user_id                     UUID PRIMARY KEY REFERENCES users (id),

    daily_notifications_enabled BOOLEAN DEFAULT TRUE,
    daily_notification_time     TIME NOT NULL,

    time_zone                   TEXT NOT NULL
);