package com.cowin.vaccinenotify;

import java.util.List;

import lombok.Data;

@Data
public class Centers {

    private long center_id;
    private String name;
    private String address;
    private String state_name;
    private String district_name;
    private int pincode;
    private String fee_type;
    List<Sessions> sessions;


    @Data
    static class Sessions {
        private String date;
        private int available_capacity;
        private int min_age_limit;
        List<String> slots;
    }

}
