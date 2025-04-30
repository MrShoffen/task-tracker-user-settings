package org.mrshoffen.tasktracker.user.settings.event;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mrshoffen.tasktracker.commons.kafka.event.registration.RegistrationAttemptEvent;
import org.mrshoffen.tasktracker.commons.kafka.event.registration.RegistrationFailedEvent;
import org.mrshoffen.tasktracker.user.settings.service.UserSettingsService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class EventListener {

    private final UserSettingsService userSettingsService;

    //Сохраняем пользователя даже при попытке регистрации. Если будет ивент на неудачную регу - откатываем
    @KafkaListener(topics = RegistrationAttemptEvent.TOPIC)
    public void handleRegistrationAttempt(RegistrationAttemptEvent event) {
        log.info("Received event in topic {} - {}", RegistrationAttemptEvent.TOPIC, event);
        userSettingsService.createDefaultSettingsForUser(event);
    }

    @KafkaListener(topics = RegistrationFailedEvent.TOPIC)
    public void handleRegistrationFail(RegistrationFailedEvent event) {
        log.info("Received event in topic {} - {}", RegistrationFailedEvent.TOPIC, event);
        userSettingsService.deleteUserById(event.getRegistrationId());

    }

}
