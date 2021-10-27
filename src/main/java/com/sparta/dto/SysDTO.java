package com.sparta.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SysDTO {

	@JsonProperty("country")
	private String country;

	@JsonProperty("sunrise")
	private Integer sunrise;

	@JsonProperty("sunset")
	private Integer sunset;

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("type")
	private Integer type;

	@JsonProperty("message")
	private Double message;

	public String getCountry(){
		return country;
	}

	public Integer getSunrise(){
		return sunrise;
	}

	public Integer getSunset(){
		return sunset;
	}

	public Integer getId(){
		return id;
	}

	public Integer getType(){
		return type;
	}

	public Double getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "SysDTO{" +
				"country='" + country + '\'' +
				", sunrise=" + sunrise +
				", sunset=" + sunset +
				", id=" + id +
				", type=" + type +
				", message=" + message +
				'}';
	}
}