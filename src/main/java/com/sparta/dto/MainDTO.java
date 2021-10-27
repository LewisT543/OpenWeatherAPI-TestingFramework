package com.sparta.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MainDTO {

	@JsonProperty("temp")
	private Double temp;

	@JsonProperty("temp_min")
	private Double tempMin;

	@JsonProperty("humidity")
	private Integer humidity;

	@JsonProperty("pressure")
	private Integer pressure;

	@JsonProperty("feels_like")
	private Double feelsLike;

	@JsonProperty("temp_max")
	private Integer tempMax;

	public Double getTemp(){
		return temp;
	}

	public Double getTempMin(){
		return tempMin;
	}

	public Integer getHumidity(){
		return humidity;
	}

	public Integer getPressure(){
		return pressure;
	}

	public Double getFeelsLike(){
		return feelsLike;
	}

	public Integer getTempMax(){
		return tempMax;
	}

	@Override
	public String toString() {
		return "MainDTO{" +
				"temp=" + temp +
				", tempMin=" + tempMin +
				", humidity=" + humidity +
				", pressure=" + pressure +
				", feelsLike=" + feelsLike +
				", tempMax=" + tempMax +
				'}';
	}
}