package com.sparta.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

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