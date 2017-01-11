package io.tailrec.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Optional;

/**
 * @author Hussachai Puripunpinyo
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IpInfo {

    private String query;

    private Optional<String> country;

    private Optional<String> city;

    private Optional<Double> lat;

    private Optional<Double> lon;

    @Override
    public String toString() {
        return "IpInfo{" +
                "query='" + query + '\'' +
                ", country=" + country +
                ", city=" + city +
                ", lat=" + lat +
                ", lon=" + lon +
                '}';
    }

    public String getQuery() {
        return query;
    }

    public Optional<String> getCountry() {
        return country;
    }

    public Optional<String> getCity() {
        return city;
    }

    public Optional<Double> getLat() {
        return lat;
    }

    public Optional<Double> getLon() {
        return lon;
    }
}
