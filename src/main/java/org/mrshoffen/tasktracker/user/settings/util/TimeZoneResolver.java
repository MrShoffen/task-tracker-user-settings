package org.mrshoffen.tasktracker.user.settings.util;

import com.fasterxml.jackson.databind.type.MapType;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.lang.reflect.Type;
import java.util.Map;


@Component
@AllArgsConstructor
public class TimeZoneResolver {

    private final RestClient ipApiClient = RestClient.create("http://ip-api.com/json");

    public String resolveTimeZoneFromIp(String ip) {
        if (ip == null) {
            return "Europe/Moscow";
        }

        Map<String, String> body = null;
        try {
            body = ipApiClient.get()
                    .uri("/{ip}?fields=timezone", ip)
                    .retrieve()
                    .body(new ParameterizedTypeReference<>() {
                    });
            if (body.get("timezone") == null) {
                return "Europe/Moscow";
            }
            return body.get("timezone");
        } catch (Exception e) {
            return "Europe/Moscow";
        }

    }
}
