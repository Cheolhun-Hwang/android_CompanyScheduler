package com.hch.hooney.companyscheduler.Recycler.Work;

/**
 * Created by hooney on 2018. 2. 2..
 */

public class Work {
    private String spotName;
    private double GpsX; //경도
    private double GpxY; //위도

    public Work( ) {
        this.spotName = "";
        GpsX = 0.0;
        GpxY = 0.0;
    }

    public Work(String spotName, double gpsX, double gpxY) {
        this.spotName = spotName;
        GpsX = gpsX;
        GpxY = gpxY;
    }

    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    public double getGpsX() {
        return GpsX;
    }

    public void setGpsX(double gpsX) {
        GpsX = gpsX;
    }

    public double getGpxY() {
        return GpxY;
    }

    public void setGpxY(double gpxY) {
        GpxY = gpxY;
    }
}
