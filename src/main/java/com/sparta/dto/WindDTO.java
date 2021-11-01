package com.sparta.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WindDTO {

	@JsonProperty("deg")
	private Integer deg;

	@JsonProperty("speed")
	private Double speed;

	@JsonProperty("gust")
	private Double gust;

	public Integer getDeg(){
		return deg;
	}

	public Double getSpeed(){
		return speed;
	}

	public Double getGust() {
		return gust;
	}

	@Override
	public String toString() {
		return "WindDTO{" +
				"deg=" + deg +
				", speed=" + speed +
				", gust=" + gust +
				'}';
	}
}