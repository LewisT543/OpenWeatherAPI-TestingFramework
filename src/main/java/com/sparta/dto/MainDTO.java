package com.sparta.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sparta.util.GenericMethods;

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

	@JsonProperty("sea_level")
	private Integer seaLevel;

	@JsonProperty("grnd_level")
	private Integer grndLevel;

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

	public Integer getSeaLevel() {
		return seaLevel;
	}

	public Integer getGrndLevel() {
		return grndLevel;
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
				", seaLevel=" + seaLevel +
				", grndLevel=" + grndLevel +
				'}';
	}


}