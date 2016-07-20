package com.opitzconsulting.com.Model;

import com.google.gson.Gson;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by OFI on 27.06.2016.
 */
public class TelemetryData {
    //public String onlinesale_resource = "onlinesale";
    public String data_status;
    public String data_mId="0-OF";
    public String data_brandid;
    public int data_amount;
    //public boolean field_online;
    //public double field_change_remaining;

    //public static final String machine_id = "66ae16f089aff06dafd86467";

    public String serialize() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
