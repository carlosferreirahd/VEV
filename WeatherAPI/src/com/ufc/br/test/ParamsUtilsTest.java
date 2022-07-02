package com.ufc.br.test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.ufc.br.ParamsUtils;

public class ParamsUtilsTest {
	
	@Test
	void testIsValidPlacenameFunc_ValidCases() {
		String [] someValidPlacenames = {"maranguape", "quixada", "3 coracoes"};
		
		for(String placename : someValidPlacenames) {
			boolean result = ParamsUtils.isValidPlacename(placename);
			assertTrue(result);
		}
	}
	
	@Test
	void testIsValidPlacenameFunc_InvalidCases() {
		// casos nulo, vazio e com simbolos
		String [] invalidPlacenames = {null, "", "quix@da", "maranguape!", "3,coracoes"};
		
		for(String placename : invalidPlacenames) {
			boolean result = ParamsUtils.isValidPlacename(placename);
			assertFalse(result);
		}
	}
	
	@Test
	void testIsValidLatitudeFunc_ValidCases() {
		Float [] someValidLatitudes = {-80.2f, 89.99f, -89.99f, 20.1221f};
		
		for(float f : someValidLatitudes) {
			boolean result = ParamsUtils.isValidLatitude(f);
			assertTrue(result);
		}
	}
	
	@Test
	void testIsValidLatitudeFunc_InvalidValidCases() {
		Float [] invalidLatitudes = {90.0001f, -90.0001f, 180.0f, 121.21312f};
		
		for(float f : invalidLatitudes) {
			boolean result = ParamsUtils.isValidLatitude(f);
			assertFalse(result);
		}
	}
	
	@Test
	void testIsValidLongitudeFunc_ValidCases() {
		Float [] someValidLongitudes = {-120.0f, 179.99f, -179.99f, 150.1231f};
		
		for(float f : someValidLongitudes) {
			boolean result = ParamsUtils.isValidLongitude(f);
			assertTrue(result);
		}
	}
	
	@Test
	void testIsValidLongitudeFunc_InvalidCases() {
		Float [] invalidLongitudes = {180.0001f, -180.0001f, 270.0f, -190.12122f};
		
		for(float f : invalidLongitudes) {
			boolean result = ParamsUtils.isValidLongitude(f);
			assertFalse(result);
		}
	}
}
