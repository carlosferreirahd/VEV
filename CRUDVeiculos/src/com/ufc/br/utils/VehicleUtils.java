package com.ufc.br.utils;

import com.ufc.br.models.Vehicle;

import java.util.List;

public class VehicleUtils {

  public static Integer findVehicleInListByLicensePlate(String licensePlate, List<Vehicle> vehicleList) {
    if (licensePlate != null && vehicleList != null) {
      Vehicle other = new Vehicle();
      other.setLicensePlate(licensePlate);

      for (int i = 0; i < vehicleList.size(); i++) {
        if (vehicleList.get(i).equals(other)) {
          return i;
        }
      }
    }

    return -1;
  }

}
