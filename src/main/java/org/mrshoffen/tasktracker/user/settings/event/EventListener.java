package org.mrshoffen.tasktracker.user.settings.event;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mrshoffen.tasktracker.commons.kafka.event.user.UserCreatedEvent;
import org.mrshoffen.tasktracker.user.settings.model.entity.UserSettings;
import org.mrshoffen.tasktracker.user.settings.service.UserSettingsService;
import org.mrshoffen.tasktracker.user.settings.util.TimeZoneResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class EventListener {

    private final UserSettingsService userSettingsService;

    @KafkaListener(topics = UserCreatedEvent.TOPIC, groupId = "${spring.kafka.consumer.group-id}")
    public void handleUserCreated(UserCreatedEvent event) {
        log.info("Received event in topic {} - {}", UserCreatedEvent.TOPIC, event);
        userSettingsService.createDefaultSettingsForUser(event.getId(), event.getUserIpAddr());

    }

}
