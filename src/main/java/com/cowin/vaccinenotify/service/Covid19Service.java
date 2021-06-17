package com.cowin.vaccinenotify.service;

import java.util.*;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cowin.vaccinenotify.CovidDataModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class Covid19Service {

    private static final String RAJASTHAN_STATE_CODE = "28";
    private static final String KARNATAKA_STATE_CODE = "15";
    private static final String MADHYA_PRADESH_STATE_CODE = "23";

    public ResponseEntity<Map<String, Object>> getDailyCoronaCases() throws JsonProcessingException {
        log.info("inside service");
        long currentTimeInMillis = new Date().getTime();
        String url = "https://www.mygov.in/sites/default/files/covid/covid_state_counts_ver1.json?" + currentTimeInMillis;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> jsonResponse = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        log.info("calling API...");
        CovidDataModel covidDataModel = objectMapper.readValue(jsonResponse.getBody(), CovidDataModel.class);
        System.out.println();
        int totalDailyCases = 0;
        for(String s : covidDataModel.getDiffConfirmedCovidCases().keySet()) {
            totalDailyCases += Integer.parseInt(covidDataModel.getDiffConfirmedCovidCases().get(s));
        }

        Map<String, Object> responseData = new HashMap<>();
        responseData.put("Total Daily Cases", totalDailyCases);
        responseData.put("data", new ArrayList<>());

        List<Object> list = new ArrayList<>();
        Map<String, String> stateDataMap = new HashMap<>();
        stateDataMap.put("state", "Rajasthan");
        stateDataMap.put("daily_active_cases", covidDataModel.getDiffActiveCovidCases().get(RAJASTHAN_STATE_CODE));
        stateDataMap.put("daily_confirmed_cases", covidDataModel.getDiffConfirmedCovidCases().get(RAJASTHAN_STATE_CODE));
        stateDataMap.put("daily_cured_cases", covidDataModel.getDiffCuredDischarged().get(RAJASTHAN_STATE_CODE));

        list.add(new HashMap<>(stateDataMap));

        stateDataMap.put("state", "Karnataka");
        stateDataMap.put("daily_active_cases", covidDataModel.getDiffActiveCovidCases().get(KARNATAKA_STATE_CODE));
        stateDataMap.put("daily_confirmed_cases", covidDataModel.getDiffConfirmedCovidCases().get(KARNATAKA_STATE_CODE));
        stateDataMap.put("daily_cured_cases", covidDataModel.getDiffCuredDischarged().get(KARNATAKA_STATE_CODE));

        list.add(new HashMap<>(stateDataMap));

        stateDataMap.put("state", "Madhya Pradesh");
        stateDataMap.put("daily_active_cases", covidDataModel.getDiffActiveCovidCases().get(MADHYA_PRADESH_STATE_CODE));
        stateDataMap.put("daily_confirmed_cases", covidDataModel.getDiffConfirmedCovidCases().get(MADHYA_PRADESH_STATE_CODE));
        stateDataMap.put("daily_cured_cases", covidDataModel.getDiffCuredDischarged().get(MADHYA_PRADESH_STATE_CODE));

        list.add(new HashMap<>(stateDataMap));
        responseData.put("data", list);

        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
