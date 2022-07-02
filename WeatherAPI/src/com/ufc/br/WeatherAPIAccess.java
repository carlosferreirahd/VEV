package com.ufc.br;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public interface WeatherAPIAccess {

//  private static final String api_key = "f606387d34994ad1adb224724221806";

//	public WeatherAPIAccess() {
//
//	}
	
	// param pode ser um nome de lugar, ou a latitude e longitude na forma decimal (ex: "-3.88,-38.67")
	public BufferedReader getWeatherInfoByParam(String param);

//	public BufferedReader getWeatherInfoByParam(String param) {
//
//		try {
//			URL url = new URL("http://api.weatherapi.com/v1/current.json?key=" + api_key + "&q=" + param + "&aqi=yes");
//
//			HttpURLConnection con = (HttpURLConnection) url.openConnection();
//			con.setRequestMethod("GET");
//			con.setRequestProperty("Content-Type", "application/json");
//			con.disconnect();
//
//			return new BufferedReader(new InputStreamReader(con.getInputStream()));
//		} catch (IOException e) {
//			return null;
//		}
//	}
}
