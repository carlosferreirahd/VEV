package com.ufc.br;

public class ParamsUtils {
	
	public static boolean isValidPlacename(String placename) {
		if (placename == null) return false;
		
		if (placename.trim().isEmpty()) return false;
		
		// se placename contem algum caractere diferente de letras, numeros ou espacos
		for(char c : placename.toCharArray()) {
			if(!(c >= 'a' && c <= 'z')
					&& !(c >= 'A' && c <= 'Z')
					&& !(c >= '0' && c <= '9')
					&& (c != ' ')) {
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean isValidLatitude(float latitude) {
		return latitude >= -90.0 && latitude <= 90.0; // latitude range
	}
	
	public static boolean isValidLongitude(float longitude) {
		return longitude >= -180.0 && longitude <= 180.0; // longitude range
	}

}
