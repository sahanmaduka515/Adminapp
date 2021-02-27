package com.example.adminapp.event;

public class event {
    public event(String name, String title, double lat, double lon) {
        this.name = name;
        this.title = title;
        this.lat = lat;
        this.lon = lon;
    }

    public event() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    String name;
    String title;
    double lat;
    double lon;


}
