package com.dayrayaneh.automation.model.pishkhan.pishkhan_bazaryabi.detail;

import com.google.gson.annotations.SerializedName;

public class DataItem {


    @SerializedName("Moshtari")
    private String moshtari;

    @SerializedName("Description")
    private String description;

    @SerializedName("FullName")
    private String fullName;

    @SerializedName("khadamatType")
    private String khadamatType;

    @SerializedName("TelNumber")
    private String telNumber;

    @SerializedName("Vaziat")
    private String vaziat;

    @SerializedName("NueMahsol")
    private String nueMahsol;

    @SerializedName("DetailsCount")
    private int detailsCount;

    @SerializedName("ProformaCode")
    private int proformaCode;

    @SerializedName("Date")
    private String date;

    @SerializedName("Golden")
    private boolean golden;

    @SerializedName("Today")
    private boolean today;

    @SerializedName("New")
    private boolean new_;


    public String getMoshtari() {
        return moshtari;
    }

    public String getDescription() {
        return description;
    }

    public String getFullName() {
        return fullName;
    }

    public String getKhadamatType() {
        return khadamatType;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public String getVaziat() {
        return vaziat;
    }

    public String getNueMahsol() {
        return nueMahsol;
    }


    public int getDetailsCount() {
        return detailsCount;
    }

    public int getProformaCode() {
        return proformaCode;
    }

    public String getDate() {
        return date;
    }

    public boolean isGolden() {
        return golden;
    }

    public boolean isToday() {
        return today;
    }

    public boolean isNew_() {
        return new_;
    }

}