package org.mrshoffen.tasktracker.user.settings.repository;

import org.mrshoffen.tasktracker.user.settings.model.entity.UserSettings;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface UserSettingsRepository extends CrudRepository<UserSettings, UUID> {
}
