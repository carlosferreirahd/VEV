package com.ufc.br;

public class Weather {
	
	private String locationName;
	private String country;
	private String region;
	private String weatherInfo;
	private String windInfo;
	private String humidityInfo;
	
	public Weather(String locationName, String country, String region, String weatherInfo, String windInfo,
			String humidityInfo) {
		this.locationName = locationName;
		this.country = country;
		this.region = region;
		this.weatherInfo = weatherInfo;
		this.windInfo = windInfo;
		this.humidityInfo = humidityInfo;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getWeatherInfo() {
		return weatherInfo;
	}
	public void setWeatherInfo(String weatherInfo) {
		this.weatherInfo = weatherInfo;
	}
	public String getWindInfo() {
		return windInfo;
	}
	public void setWindInfo(String windInfo) {
		this.windInfo = windInfo;
	}
	public String getHumidityInfo() {
		return humidityInfo;
	}
	public void setHumidityInfo(String humidityInfo) {
		this.humidityInfo = humidityInfo;
	}
	@Override
	public String toString() {
		return "Weather [locationName=" + locationName + ", country=" + country + ", region=" + region + ", weatherInfo="
				+ weatherInfo + ", windInfo=" + windInfo + ", humidityInfo=" + humidityInfo + "]";
	}
	
}
