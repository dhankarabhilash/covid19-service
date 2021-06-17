package com.cowin.vaccinenotify;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cowin.vaccinenotify.service.Covid19Service;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/v1")
@Slf4j
public class CovidController {

    @Autowired
    private Covid19Service covid19Service;

    @GetMapping("/corona/daily")
    public ResponseEntity<Map<String, Object>> getCoronaCases() throws JsonProcessingException {
        log.info("inside controller");
        return covid19Service.getDailyCoronaCases();
    }

}
