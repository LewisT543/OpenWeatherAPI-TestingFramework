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

	public boolean isWeatherItemDTOValid(WeatherItemDTO weatherItemDTO) {
		if (!WeatherCodes.hasReadFile()) {
			WeatherCodes.readWeatherCodes();
		}
		// id, main, description, icon must exist i.e. not be null
		if (!(isNotNull(weatherItemDTO.getId()) &&
				isNotNull(weatherItemDTO.getMain()) &&
				isNotNull(weatherItemDTO.getDescription()) &&
				isNotNull(weatherItemDTO.getIcon()))) {
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
	//Check type stuff


	// Helper A
	public boolean getSunriseSunsetRequestEqual(Long sunrise, Long sunset, Long dt) {
		return getDateComparison(sunrise, dt) && getDateComparison(sunset, dt);
	}

	// Helper B
	public boolean SunriseAndSunsetOnSameDay(Long sunrise, Long sunset) {
		return getDateComparison(sunrise, sunset);
	}

	// Helper C
	public boolean getSunriseSunsetComparison(Long sunrise, Long sunset) {
		return sunset >= sunrise;
	}

	// helper D
	public boolean SunriseAndSunsetValid(Long sunrise, Long sunset, Long dt) {
		return getSunriseSunsetComparison(sunrise, sunset)
					&& SunriseAndSunsetOnSameDay(sunrise, sunset)
					&& getSunriseSunsetRequestEqual(sunrise, sunset, dt);
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
		return isDouble(getSys().getMessage());
	}

	public boolean isDtALong(){
		return isLong(getDt());
	}

	public boolean isSnowH1Double(){
		return isDouble(getSnow().getOneHour());
	}

	public boolean isSnowH3Double(){
		return isDouble(getSnow().getThreeHour());
	}

	public boolean isRainH1Double(){
		return isDouble(getRain().getOneHour());
	}

	public boolean isRainH3Double(){
		return isDouble(getRain().getThreeHour());
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
		return isDouble(getCoord().getLon());
	}
	public boolean isCoordLatADouble() {
		return isDouble(getCoord().getLat());
	}

	public boolean isBaseAString() {
		return isString(getBase());
	}

	public boolean isTempADouble() {
		return isDouble(getMain().getTemp());
	}

	public boolean isFeelsLikeADouble() {
		return isDouble(getMain().getFeelsLike());
	}

	public boolean isTempMinADouble() {
		return isDouble(getMain().getTempMin());
	}

	public boolean isTempMaxADouble() {
		return isDouble(getMain().getTempMax());
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
		return isDouble(getWind().getSpeed());
	}

	public boolean isWindDegreeAnInt() {
		return isInteger(getWind().getDeg());
	}

	public boolean isWindGustisDouble() {
		return isDouble(getWind().getGust());
	}

	public boolean isCloudsAnInt() {
		return isInteger(getClouds().getAll());
	}

	public boolean isMaxTempGreaterThanZeroKelvin() {
		return getMain().getTempMax() > 0;
	}

	public boolean isMinTempGreaterThanZeroKelvin() {

		return getMain().getTempMin() > 0;

	}
	public boolean isMaxTempGreaterThanZeroCelsius () {
		return mainDTO.getTempMax() > -273;
	}

	public boolean isMinTempGreaterThanZeroCelsius () {
		return mainDTO.getTempMin() > -273;
	}

	public boolean isMaxTempGreaterThanZeroFahrenheit () {
		return mainDTO.getTempMax() > -459.67;
	}

	public boolean isMinTempGreaterThanZeroFahrenheit () {
		return mainDTO.getTempMin() > -459.67;
	}

	public boolean isCloudsAllGreaterThanOrEqualTo0AndLessThan100 () {
			return isBetweenXAndY(getClouds().getAll().doubleValue(), 0.0, 100.0);
	}

	public boolean isMainHumidityGreaterThan0AndLessThan100 () {
			return isBetweenXAndY(getMain().getHumidity().doubleValue(), 0.0, 100.0);
	}

	public boolean isCoordLatGreaterThanMinus90AndLessThan90 () {
			return isBetweenXAndY(getCoord().getLat(), -90.0, 90.0);
	}

	public boolean isCoordLonGreaterThanMinus180AndLessThan180 () {
			return isBetweenXAndY(getCoord().getLon(), -180.0, 180.0);
	}

	public boolean isCorrectCityID() {
		return cityIdIsCorrect(getName(), getSys().getId());
	}

	public boolean isCorrectCityLon() {
		return cityLonIsCorrect(getName(), getCoord().getLon());
	}

	public boolean isCorrectCityLat() {
		return cityLatIsCorrect(getName(), getCoord().getLat());
	}

	public boolean isTempGreaterThan0Kelvin(){
		return isGreaterThanOrEqualTo(getMain().getTemp(),0.0);
	}
  
	public boolean isTempGreaterThanMinus273Celcius(){
		return isGreaterThanOrEqualTo(getMain().getTemp(),-273.0);
  }
  
	public boolean isTempGreaterThanMinus459Fahrenheit(){
		return isGreaterThanOrEqualTo(getMain().getTemp(),-459.67);
	}
  
	public boolean isFeelsLikeStandardGreaterThanMin(){
		return isGreaterThanOrEqualToZero(getMain().getFeelsLike());
	}

	public boolean isFeelsLikeMetricGreaterThanMin(){
		return isGreaterThanOrEqualTo(getMain().getFeelsLike(),-273.0);
	}

	public boolean isFeelsLikeImperialGreaterThanMin(){
		return isGreaterThanOrEqualTo(getMain().getFeelsLike(),-459.67);
	}

	public boolean isRainH1GreaterThanOrEqualToZero() {
		return isGreaterThanOrEqualToZero(getRain().getOneHour());
	}

	public boolean isRainH3GreaterThanOrEqualToZero() {
		return isGreaterThanOrEqualToZero(getRain().getThreeHour());
	}

	public boolean isSeaLevelGreaterThanOrEqualToZero() {
		return isGreaterThanOrEqualToZero(getMain().getSeaLevel());
	}

	public boolean isWindSpeedGreaterThanOrEqualToZero() {
		return isGreaterThanOrEqualToZero(getWind().getSpeed());
	}

	public boolean isMinTempLessThanMaxTempKelvin() {
		return (getMain().getTempMin() <= getMain().getTempMax());
	}

	public boolean isPressureGreaterOrEqualToZero() {
		return isGreaterThanOrEqualToZero(getMain().getPressure());
	}

	public boolean isGustGreaterOrEqualToZero() {
		return isGreaterThanOrEqualToZero(getWind().getGust());
	}

	public boolean isGroundPressureGreaterOrEqualToZero() {
		return isGreaterThanOrEqualToZero(getMain().getGrndLevel());
	}

	public boolean isSnowVolumeInLast1HGreaterOrEqualToZero() {
		return isGreaterThanOrEqualToZero(getSnow().getOneHour());
	}

	public boolean isSnowVolumeInLast3HGreaterOrEqualToZero() {
		return isGreaterThanOrEqualToZero(getSnow().getThreeHour());
	}

	public boolean isSunsetTime(){
		return isNotNull(getSys().getSunset());
	}

	public boolean isSunriseTime(){
		return isNotNull(getSys().getSunrise());
	}

	public boolean isSunsetTimeLong(){
		return isLong(sysDTO.getSunset());
	}

	public boolean isSunriseTimeLong(){
		return isLong(sysDTO.getSunrise());
	}

	public boolean isLongSunsetTime(){
		return isLong(getSys().getSunset());
	}

	public boolean isLongSunriseTime(){
		return isLong(getSys().getSunrise());
	}

	public boolean isValidSunsetTime(){
		return epochDateIsValid(getSys().getSunset()) &&
				epochIsTenDigits(getSys().getSunset());
	}

	public boolean isValidSunriseTime(){
		return epochDateIsValid(getSys().getSunrise()) &&
				epochIsTenDigits(getSys().getSunrise());
	}

	// What if sunrise/sunset don't occur in the same day?
	public boolean isCorrectSunsetData(){
		return getDateComparison(getSys().getSunset(), System.currentTimeMillis());
	}

	public boolean isCorrectSunriseData(){
		return getDateComparison(getSys().getSunset(), System.currentTimeMillis());
	}
}