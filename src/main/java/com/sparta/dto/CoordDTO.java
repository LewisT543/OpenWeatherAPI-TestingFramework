package com.sparta.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CoordDTO {

	@JsonProperty("lon")
	private Double lon;

	@JsonProperty("lat")
	private Double lat;

	public Double getLon(){
		return lon;
	}

	public Double getLat(){
		return lat;
	}

	@Override
	public String toString() {
		return "CoordDTO{" +
				"lon=" + lon +
				", lat=" + lat +
				'}';
	}
}