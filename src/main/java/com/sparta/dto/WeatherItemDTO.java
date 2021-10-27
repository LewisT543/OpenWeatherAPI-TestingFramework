package com.sparta.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherItemDTO {

	@JsonProperty("icon")
	private String icon;

	@JsonProperty("description")
	private String description;

	@JsonProperty("main")
	private String main;

	@JsonProperty("id")
	private Integer id;

	public String getIcon(){
		return icon;
	}

	public String getDescription(){
		return description;
	}

	public String getMain(){
		return main;
	}

	public Integer getId(){
		return id;
	}

	@Override
	public String toString() {
		return "WeatherItemDTO{" +
				"icon='" + icon + '\'' +
				", description='" + description + '\'' +
				", main='" + main + '\'' +
				", id=" + id +
				'}';
	}
}