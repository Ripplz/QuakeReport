package com.example.android.quakereport;

/**
 * Created by DELL on 11/20/2017.
 * An {@link EarthQuake} object contains information related to a single earthquake.
 */

public class EarthQuake {

    /* Magnitude of the earthquake */
    private double magnitude;

    /* Location of the earthquake */
    private String location;

    /* Time the earthquake happened */
    private long timeInMilliseconds;

    /* The url link to the webpage containing details of the earthquake */
    private String url;


    /**
     * Constructs a new {@link EarthQuake} object
     *
     * @param magnitude is the magnitude (size) of the earthquake
     * @param location is the city location of the earthquake
     * @param timeInMilliseconds is the date the earthquake happened
     * @param url is the link to the USGS website that displays the details of the earthquake.
     */
    public EarthQuake(double magnitude, String location, long timeInMilliseconds, String url) {
        this.magnitude = magnitude;
        this.location = location;
        this.timeInMilliseconds = timeInMilliseconds;
        this.url = url;
    }

    /**
     * @return the magnitude of the earthquake
     */
    public double getMagnitude() {
        return magnitude;
    }

    /**
     * @return the city location of the earthquake
     */
    public String getLocation() {
        return location;
    }

    /**
     * @return the date the earthquake happened
     */
    public long getTimeInMilliseconds() {
        return timeInMilliseconds;
    }

    public String getUrl() {
        return url;
    }
}
