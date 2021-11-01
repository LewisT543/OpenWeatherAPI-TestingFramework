package com.sparta.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SnowDTO {

    // don't know if these should be double or int??

    @JsonProperty("1h")
    private Double oneHour;

    @JsonProperty("3h")
    private Double threeHour;

    public Double getOneHour() {
        return oneHour;
    }

    public Double getThreeHour() {
        return threeHour;
    }

    @Override
    public String toString() {
        return "SnowDTO{" +
                "oneHour=" + oneHour +
                ", threeHour=" + threeHour +
                '}';
    }
}
