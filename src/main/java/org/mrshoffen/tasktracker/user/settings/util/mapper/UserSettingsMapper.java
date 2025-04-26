package org.mrshoffen.tasktracker.user.settings.util.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mrshoffen.tasktracker.user.settings.model.dto.UserSettingsDto;
import org.mrshoffen.tasktracker.user.settings.model.entity.UserSettings;


@Mapper(componentModel = "spring")
public interface UserSettingsMapper {

    UserSettingsDto toDto(UserSettings user);

}
