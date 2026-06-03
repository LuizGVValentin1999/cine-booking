package com.cine.cinebooking.dto;

public class InfoResponse {

    private String appName;
    private String status;

    public InfoResponse() {
    }

    public InfoResponse(String appName, String status) {
        this.appName = appName;
        this.status = status;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}