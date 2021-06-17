package com.cowin.vaccinenotify;

import java.util.HashMap;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CovidDataModel {

    @JsonProperty("Total Confirmed cases")
    private HashMap<String, String> totalConfirmedCases;

    @JsonProperty("Active")
    private HashMap<String, String> activeCases;

    @JsonProperty("Cured/Discharged/Migrated")
    private HashMap<String, String> curedCases;

    @JsonProperty("Death")
    private HashMap<String, String> deathCases;

    @JsonProperty("last_confirmed_covid_cases")
    private HashMap<String, String> lastConfirmedCovidCases;

    @JsonProperty("last_cured_discharged")
    private HashMap<String, String> lastCuredDischarged;

    @JsonProperty("last_death")
    private HashMap<String, String> lastDeath;

    @JsonProperty("last_active_covid_cases")
    private HashMap<String, String> lastActiveCovidCases;

    @JsonProperty("diff_confirmed_covid_cases")
    private HashMap<String, String> diffConfirmedCovidCases; // Daily corona casees

    @JsonProperty("diff_cured_discharged")
    private HashMap<String, String> diffCuredDischarged;

    @JsonProperty("diff_death")
    private HashMap<String, String> diffDeath;

    @JsonProperty("diff_active_covid_cases")
    private HashMap<String, String> diffActiveCovidCases;

}
