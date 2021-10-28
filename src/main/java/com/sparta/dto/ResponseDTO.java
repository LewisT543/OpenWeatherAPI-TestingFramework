package com.sparta.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

	@JsonProperty("snow")
	private SnowDTO snowDTO;  //THIS WAS ADDED MANUALLY

	@JsonProperty("rain")
	private RainDTO rainDTO;  //THIS WAS ADDED MANUALLY

	@JsonProperty("dt")
	private Long dt;

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

	public SnowDTO getSnow(){  //THIS WAS ADDED MANUALLY
		return snowDTO;
	}

	public RainDTO getRain(){  //THIS WAS ADDED MANUALLY
		return rainDTO;
	}

	public SysDTO getSys(){
		return sysDTO;
	}

	public Long getDt(){
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

	//Check type stuff

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


	public boolean isCodInteger(){
		return isInteger(getCod());
	}

	public boolean isNameString(){
		return isString(getName());
	}

	public boolean isIdInt(){
		return isInteger(getId());
	}

	public boolean isTimeZoneInteger(){
		return isInteger(getTimezone());
	}

	public boolean isSysSunsetALong(){
		return isLong(getSys().getSunset());
	}

	public boolean isSysSunriseALong(){
		return isLong(getSys().getSunrise());
	}

	public boolean isSysCountryString(){
		return isString(getSys().getCountry());
	}

	public boolean isSysMessageDouble(){
		return isADouble(getSys().getMessage());
	}

	public boolean isDtALong(){
		return isLong(getDt());
	}

	public boolean isSnowH1Double(){
		return isADouble(getSnow().getOneHour());
	}

	public boolean isSnowH3Double(){
		return isADouble(getSnow().getThreeHour());
	}

	public boolean isRainH1Double(){
		return isADouble(getRain().getOneHour());
	}

	public boolean isRainH3Double(){
		return isADouble(getRain().getThreeHour());
	}
	
	//WEATHER METHODS NEEDS TO BE CHECKED
	public boolean isWeatherIdAnInt() {
		//Weather is a list of WeatherDTO's
		return isInteger(getWeather().get(0).getId());
	}
	public boolean isWeatherMainAString() {
		//Weather is a list of WeatherDTO's
		return isString(getWeather().get(0).getMain());
	}

	public boolean isWeatherDescriptionAString() {
		//Weather is a list of WeatherDTO's
		return isString(getWeather().get(0).getDescription());
	}

	public boolean isWeatherIconAString() {
		//Weather is a list of WeatherDTO's
		return isString(getWeather().get(0).getIcon());
	}
	//END OF WEATHER METHODS

	public boolean isCoordLonADouble() {
		return isADouble(getCoord().getLon());
	}
	public boolean isCoordLatADouble() {
		return isADouble(getCoord().getLat());
	}

	public boolean isBaseAString() {
		return isString(getBase());
	}

	public boolean isTempADouble() {
		return isADouble(getMain().getTemp());
	}

	public boolean isFeelsLikeADouble() {
		return isADouble(getMain().getFeelsLike());
	}

	public boolean isTempMinADouble() {
		return isADouble(getMain().getTempMin());
	}

	public boolean isTempMaxADouble() {
		return isADouble(getMain().getTempMax());
	}

	public boolean isPressureAnInt() {
		return isInteger(getMain().getPressure());
	}

	public boolean isHumidityAnInt() {
		return isInteger(getMain().getHumidity());
	}


	public boolean isSeaLevelAnInt() {

		return isInteger(getMain().getSeaLevel());
	}

	public boolean isGroundLevelAnInt() {
		return isInteger(getMain().getGrndLevel());
	}

	public boolean isWindSpeedADouble() {
		return isADouble(getWind().getSpeed());
	}

	public boolean isWindDegreeAnInt() {
		return isInteger(getWind().getDeg());
	}

	public boolean isWindGustIsADouble() {
		return isADouble(getWind().getGust());
	}

	public boolean isCloudsAnInt() {
		return isInteger(getClouds().getAll());
	}
}