package com.ufc.br;

import java.io.BufferedReader;
import java.io.IOException;

import org.json.JSONObject;

public class WeatherAPIEndpointCaller {
	private WeatherAPIAccess apiAccess;
	
	public WeatherAPIEndpointCaller(WeatherAPIAccess apiAccess) {
		this.apiAccess = apiAccess;
	}
	
	public String getWeatherInfoByPlaceName(String placename) {
		if (!ParamsUtils.isValidPlacename(placename)) return null;
		
		String result = this.callEndpoint(placename);
		
		if(result == null) return null;
		
		JSONObject responseJson = new JSONObject(result);
    
		String responseLocationName = responseJson.getJSONObject("location").getString("name");
		String responseRegion = responseJson.getJSONObject("location").getString("region");
		String responseCountry = responseJson.getJSONObject("location").getString("country");
		String responseWeather = responseJson.getJSONObject("current").getJSONObject("condition").getString("text");
		String responseHumidity = responseJson.getJSONObject("current").get("humidity").toString();
		String responseWindMph = responseJson.getJSONObject("current").get("wind_mph").toString();
		
		Weather weather = new Weather(responseLocationName, responseCountry, responseRegion, responseWeather, responseWindMph, responseHumidity);
    
		return weather.toString();
	}
	
	public String getWeatherInfoByLatAndLong(float latitude, float longitude) {
		if (!ParamsUtils.isValidLatitude(latitude) || !ParamsUtils.isValidLongitude(longitude)) return null;
		
		String param = latitude + "," + longitude;
		
		String result = this.callEndpoint(param);
		
		if(result == null) return null;
		
		JSONObject responseJson = new JSONObject(result);
    
		String responseLocationName = responseJson.getJSONObject("location").getString("name");
		String responseRegion = responseJson.getJSONObject("location").getString("region");
		String responseCountry = responseJson.getJSONObject("location").getString("country");
		String responseWeather = responseJson.getJSONObject("current").getJSONObject("condition").getString("text");
		String responseHumidity = responseJson.getJSONObject("current").get("humidity").toString();
		String responseWindMph = responseJson.getJSONObject("current").get("wind_mph").toString();
		
		Weather weather = new Weather(responseLocationName, responseCountry, responseRegion, responseWeather, responseWindMph, responseHumidity);
    
		return weather.toString();
	}
	
	private String callEndpoint(String param) {
		try {
			BufferedReader in = this.apiAccess.getWeatherInfoByParam(param);
			
			if(in == null) {
				return null;
			}
			
			String inputLine;
			StringBuffer content = new StringBuffer();
			
			while((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			
			in.close();
			
			return content.toString();
		} catch(IOException e) {
			return null;
		}
	}
}
