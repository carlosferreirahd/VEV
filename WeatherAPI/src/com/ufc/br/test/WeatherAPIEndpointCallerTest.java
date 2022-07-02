package com.ufc.br.test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.io.BufferedReader;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.ufc.br.WeatherAPIAccess;
import com.ufc.br.WeatherAPIEndpointCaller;

public class WeatherAPIEndpointCallerTest {
	
	String validApiResponse = "{\n"
			+ "    \"location\": {\n"
			+ "        \"name\": \"Maranguape\",\n"
			+ "        \"region\": \"Ceara\",\n"
			+ "        \"country\": \"Brazil\",\n"
			+ "        \"lat\": -3.88,\n"
			+ "        \"lon\": -38.67,\n"
			+ "        \"tz_id\": \"America/Fortaleza\",\n"
			+ "        \"localtime_epoch\": 1656624656,\n"
			+ "        \"localtime\": \"2022-06-30 18:30\"\n"
			+ "    },\n"
			+ "    \"current\": {\n"
			+ "        \"last_updated_epoch\": 1656624600,\n"
			+ "        \"last_updated\": \"2022-06-30 18:30\",\n"
			+ "        \"temp_c\": 27.0,\n"
			+ "        \"temp_f\": 80.6,\n"
			+ "        \"is_day\": 0,\n"
			+ "        \"condition\": {\n"
			+ "            \"text\": \"Clear\",\n"
			+ "            \"icon\": \"//cdn.weatherapi.com/weather/64x64/night/113.png\",\n"
			+ "            \"code\": 1000\n"
			+ "        },\n"
			+ "        \"wind_mph\": 10.5,\n"
			+ "        \"wind_kph\": 16.9,\n"
			+ "        \"wind_degree\": 100,\n"
			+ "        \"wind_dir\": \"E\",\n"
			+ "        \"pressure_mb\": 1012.0,\n"
			+ "        \"pressure_in\": 29.88,\n"
			+ "        \"precip_mm\": 0.0,\n"
			+ "        \"precip_in\": 0.0,\n"
			+ "        \"humidity\": 74,\n"
			+ "        \"cloud\": 0,\n"
			+ "        \"feelslike_c\": 29.9,\n"
			+ "        \"feelslike_f\": 85.8,\n"
			+ "        \"vis_km\": 10.0,\n"
			+ "        \"vis_miles\": 6.0,\n"
			+ "        \"uv\": 7.0,\n"
			+ "        \"gust_mph\": 8.3,\n"
			+ "        \"gust_kph\": 13.3\n"
			+ "    }\n"
			+ "}";
	
	
	/* 			STUBS			 */
	
	@Test
	void testCallApiForPlacename_ValidCase_Stub() throws Exception {
		
		// init
		String expectedCallerReturn = "Weather [locationName=" + "Maranguape" + ", country=" + "Brazil" + ", region="
				+ "Ceara" + ", weatherInfo=" + "Clear" + ", windInfo=" + "10.5" + ", humidityInfo=" + "74" + "]";

		WeatherAPIAccess apiAccess = Mockito.mock(WeatherAPIAccess.class);
		BufferedReader in = Mockito.mock(BufferedReader.class);
		
		// config
		Mockito.when(apiAccess.getWeatherInfoByParam("maranguape")).thenReturn(in);
		Mockito.when(in.readLine()).thenReturn(validApiResponse, null);

		WeatherAPIEndpointCaller caller = new WeatherAPIEndpointCaller(apiAccess);
		
		// exercitar
		String callerReturn = caller.getWeatherInfoByPlaceName("maranguape");
		
		// verificar
		verify(in, Mockito.times(2)).readLine();
		assertEquals(expectedCallerReturn, callerReturn);
		
	}
	
	@Test
	void testCallApiForPlacename_InvalidCase_InvalidPlacename_Stub() throws Exception {
		
		// init
		String invalidApiResponse = null; // deve sempre retorna null em caso de falha na chamada de enpoint

		WeatherAPIAccess apiAccess = Mockito.mock(WeatherAPIAccess.class);
		BufferedReader in = Mockito.mock(BufferedReader.class);
		
		// config
		Mockito.when(apiAccess.getWeatherInfoByParam("inv@l1d_str1ng")).thenReturn(in);
		Mockito.when(in.readLine()).thenReturn(invalidApiResponse);

		WeatherAPIEndpointCaller caller = new WeatherAPIEndpointCaller(apiAccess);
		
		// exercitar
		String callerReturn = caller.getWeatherInfoByPlaceName("inv@l1d_str1ng");
		
		// verificar
		assertNull(callerReturn);
	}
	
	@Test
	void testCallApiForPlacename_InvalidCase_NullPlacename_Stub() throws Exception {
		
		// init
		String invalidApiResponse = null; // deve sempre retorna null em caso de falha na chamada de enpoint

		WeatherAPIAccess apiAccess = Mockito.mock(WeatherAPIAccess.class);
		BufferedReader in = Mockito.mock(BufferedReader.class);
		
		// config
		Mockito.when(apiAccess.getWeatherInfoByParam(null)).thenReturn(in);
		Mockito.when(in.readLine()).thenReturn(invalidApiResponse);

		WeatherAPIEndpointCaller caller = new WeatherAPIEndpointCaller(apiAccess);
		
		// exercitar
		String callerReturn = caller.getWeatherInfoByPlaceName(null);
		
		// verificar
		assertNull(callerReturn);
	}
	
	@Test
	void testCallApiForLatAndLong_ValidCase_Stub() throws Exception {
		
		// init
		String expectedCallerReturn = "Weather [locationName=" + "Maranguape" + ", country=" + "Brazil" + ", region="
				+ "Ceara" + ", weatherInfo=" + "Clear" + ", windInfo=" + "10.5" + ", humidityInfo=" + "74" + "]";

		WeatherAPIAccess apiAccess = Mockito.mock(WeatherAPIAccess.class);
		BufferedReader in = Mockito.mock(BufferedReader.class);
		
		// config
		Mockito.when(apiAccess.getWeatherInfoByParam("-3.88,-38.67")).thenReturn(in);
		Mockito.when(in.readLine()).thenReturn(validApiResponse, null);

		WeatherAPIEndpointCaller caller = new WeatherAPIEndpointCaller(apiAccess);
		
		// exercitar
		String callerReturn = caller.getWeatherInfoByLatAndLong(-3.88f,-38.67f);
		
		// verificar
		verify(in, Mockito.times(2)).readLine();
		assertEquals(expectedCallerReturn, callerReturn);
		
	}
	
	@Test
	void testCallApiForLatAndLong_InvalidCase_InvalidLatitude_Stub() throws Exception {
		
		// init
		String invalidApiResponse = null; // deve sempre retorna null em caso de falha na chamada de enpoint

		WeatherAPIAccess apiAccess = Mockito.mock(WeatherAPIAccess.class);
		BufferedReader in = Mockito.mock(BufferedReader.class);
		
		// config
		Mockito.when(apiAccess.getWeatherInfoByParam("-100.12,170.7")).thenReturn(in);
		Mockito.when(in.readLine()).thenReturn(invalidApiResponse);

		WeatherAPIEndpointCaller caller = new WeatherAPIEndpointCaller(apiAccess);
		
		// exercitar
		String callerReturn = caller.getWeatherInfoByLatAndLong(-100.12f,170.7f);
		
		// verificar
		assertNull(callerReturn);
	}
	
	@Test
	void testCallApiForLatAndLong_InvalidCase_InvalidLongitude_Stub() throws Exception {
		
		// init
		String invalidApiResponse = null; // deve sempre retorna null em caso de falha na chamada de enpoint

		WeatherAPIAccess apiAccess = Mockito.mock(WeatherAPIAccess.class);
		BufferedReader in = Mockito.mock(BufferedReader.class);
		
		// config
		Mockito.when(apiAccess.getWeatherInfoByParam("80.3,-190.1")).thenReturn(in);
		Mockito.when(in.readLine()).thenReturn(invalidApiResponse);

		WeatherAPIEndpointCaller caller = new WeatherAPIEndpointCaller(apiAccess);
		
		// exercitar
		String callerReturn = caller.getWeatherInfoByLatAndLong(80.3f,-190.1f);
		
		// verificar
		assertNull(callerReturn);
	}
	
	
	
	/* 			MOCKS			 */
	
	@Test
	void testCallApiForPlacename_Mock() throws Exception {
		// init
		ArgumentCaptor<String> placenameArg = ArgumentCaptor.forClass(String.class);
		
		WeatherAPIEndpointCaller caller = Mockito.mock(WeatherAPIEndpointCaller.class);
		
		String expectedCallerReturn = "Weather [locationName=" + "Maranguape" + ", country=" + "Brazil" + ", region="
				+ "Ceara" + ", weatherInfo=" + "Clear" + ", windInfo=" + "10.5" + ", humidityInfo=" + "74" + "]";
		
		// config
		Mockito.doReturn(expectedCallerReturn).when(caller).getWeatherInfoByPlaceName(placenameArg.capture());
		
		// exercitar func placename
		String result = caller.getWeatherInfoByPlaceName("maranguape");
		
		// verificar
		assertEquals(expectedCallerReturn, result);
		Mockito.verify(caller, Mockito.times(1)).getWeatherInfoByPlaceName(placenameArg.getValue());
		assertEquals("maranguape", placenameArg.getValue());
	}
	
	@Test
	void testCallApiLatAndLong_Mock() throws Exception {
		
		// init
		ArgumentCaptor<Float> latArg = ArgumentCaptor.forClass(Float.class);
		ArgumentCaptor<Float> longArg = ArgumentCaptor.forClass(Float.class);
		
		WeatherAPIEndpointCaller caller = Mockito.mock(WeatherAPIEndpointCaller.class);
		
		String expectedCallerReturn = "Weather [locationName=" + "Maranguape" + ", country=" + "Brazil" + ", region="
				+ "Ceara" + ", weatherInfo=" + "Clear" + ", windInfo=" + "10.5" + ", humidityInfo=" + "74" + "]";
		
		// config
		Mockito.doReturn(expectedCallerReturn).when(caller).getWeatherInfoByLatAndLong(latArg.capture(), longArg.capture());
		
		// exercitar func latitude e longitude
		String result = caller.getWeatherInfoByLatAndLong(-3.88f,-38.67f);
		
		// verificar
		assertEquals(expectedCallerReturn, result);
		Mockito.verify(caller, Mockito.times(1)).getWeatherInfoByLatAndLong(latArg.getValue(), longArg.getValue());
		assertEquals(-3.88f, latArg.getValue());
		assertEquals(-38.67f, longArg.getValue());
	}
	
}
