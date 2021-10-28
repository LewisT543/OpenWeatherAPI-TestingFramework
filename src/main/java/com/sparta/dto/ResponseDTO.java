package com.sparta.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sparta.util.GenericMethods;
import com.sparta.util.WeatherCodes;

import java.util.ArrayList;
import java.util.List;

import static com.sparta.util.GenericMethods.*;

public class ResponseDTO{

	@JsonProperty("visibility")
	private Integer visibility;

	@JsonProperty("timezone")
	private Integer timezone;

	@JsonProperty("main")
	private MainDTO mainDTO;

	@JsonProperty("clouds")
	private CloudsDTO cloudsDTO;

	@JsonProperty("sys")
	private SysDTO sysDTO;

	@JsonProperty("dt")
	private Integer dt;

	@JsonProperty("coord")
	private CoordDTO coordDTO;

	@JsonProperty("weather")
	private List<WeatherItemDTO> weather;

	@JsonProperty("name")
	private String name;

	@JsonProperty("cod")
	private Integer cod;

	@JsonProperty("id")
	private Integer id;

	@JsonProperty("base")
	private String base;

	@JsonProperty("wind")
	private WindDTO windDTO;

	public Integer getVisibility(){
		return visibility;
	}

	public Integer getTimezone(){
		return timezone;
	}

	public MainDTO getMain(){
		return mainDTO;
	}

	public CloudsDTO getClouds(){
		return cloudsDTO;
	}

	public SysDTO getSys(){
		return sysDTO;
	}

	public Integer getDt(){
		return dt;
	}

	public CoordDTO getCoord(){
		return coordDTO;
	}

	public List<WeatherItemDTO> getWeather(){
		return weather;
	}

	public String getName(){
		return name;
	}

	public Integer getCod(){
		return cod;
	}

	public Integer getId(){
		return id;
	}

	public String getBase(){
		return base;
	}

	public WindDTO getWind(){
		return windDTO;
	}

	public boolean isWeatherItemDTOValid(WeatherItemDTO weatherItemDTO) {
		if (!WeatherCodes.hasReadFile()) {
			WeatherCodes.readWeatherCodes();
		}
		// id, main, description, icon must exist i.e. not be null
		if (!(IsNotNull(weatherItemDTO.getId()) &&
				IsNotNull(weatherItemDTO.getMain()) &&
				IsNotNull(weatherItemDTO.getDescription()) &&
				IsNotNull(weatherItemDTO.getIcon()))) {
			return false;
		}

		ArrayList<String> fileValues = WeatherCodes.getWeatherCode(weatherItemDTO.getId());

		if (fileValues == null) {
			return false;
		}

		// main, description, icon must match up to id
		return weatherItemDTO.getMain().equals(fileValues.get(0)) &&
				weatherItemDTO.getDescription().equals(fileValues.get(1)) &&
				(weatherItemDTO.getIcon().equals(fileValues.get(2)) || weatherItemDTO.getIcon().equals(fileValues.get(3)));
	}

	@Override
	public String toString() {
		return "ResponseDTO{" +
				"\n\tvisibility=" + visibility +
				", \n\ttimezone=" + timezone +
				", \n\tmainDTO=" + mainDTO +
				", \n\tcloudsDTO=" + cloudsDTO +
				", \n\tsysDTO=" + sysDTO +
				", \n\tdt=" + dt +
				", \n\tcoordDTO=" + coordDTO +
				", \n\tweather=" + weather +
				", \n\tname='" + name + '\'' +
				", \n\tcod=" + cod +
				", \n\tid=" + id +
				", \n\tbase='" + base + '\'' +
				", \n\twindDTO=" + windDTO +
				"\n}";
	}
}