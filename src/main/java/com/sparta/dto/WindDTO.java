package com.sparta.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WindDTO {

	@JsonProperty("deg")
	private Integer deg;

	@JsonProperty("speed")
	private Double speed;

	public Integer getDeg(){
		return deg;
	}

	public Double getSpeed(){
		return speed;
	}

	@Override
	public String toString() {
		return "WindDTO{" +
				"deg=" + deg +
				", speed=" + speed +
				'}';
	}
}