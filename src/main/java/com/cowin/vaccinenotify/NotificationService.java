package com.cowin.vaccinenotify;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class NotificationService {

    @Autowired
    private EmailService emailService;

    //@Scheduled(cron = "*/3 * * * * *")
    public void notifyAlert() throws IOException {
        String url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByDistrict?district_id=506&date=16-05-2021";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<String> jsonResponse = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        String response = jsonResponse.getBody().substring(11, jsonResponse.getBody().length()-1);
        List<Centers> centersList = objectMapper.readValue(response, new TypeReference<List<Centers>>() {
        });

        System.out.println(new Date().toString());
        boolean flag = false;
        for(Centers center : centersList) {
            List<Centers.Sessions> sessionsList = center.getSessions();
            for(Centers.Sessions session : sessionsList) {
                if(session.getMin_age_limit() == 18 && session.getAvailable_capacity() > 0) {
                    emailService.sendSimpleMessage();
                    System.out.println("Email Sent at " + new Date().toString());
                    flag = true;
                    break;
                }
            }
            if(flag) break;
        }
    }
}
