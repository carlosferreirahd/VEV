package com.ufc.br.utils;

import com.ufc.br.models.Vehicle;

import java.util.List;

public class Validator {

  public Validator() {

  }

  public Boolean canInsertNewVehicle(Vehicle vehicle, List<Vehicle> vehicleList) {
    if (this.isValidVehicle(vehicle) && vehicleList != null) {
      int position = VehicleUtils.findVehicleInListByLicensePlate(vehicle.getLicensePlate(), vehicleList);
      return position == -1;
    }
    return false;
  }

  public Boolean canEditVehicle(Vehicle vehicle, List<Vehicle> vehicleList) {
    if (vehicle != null && isValidVehicle(vehicle) && vehicleList != null) {
      int pos = VehicleUtils.findVehicleInListByLicensePlate(vehicle.getLicensePlate(), vehicleList);

      if (pos != -1) {
        Vehicle v = vehicleList.get(pos);
        return v != null
            && v.getType() != null
            && v.getLicensePlate() != null
            && v.getModel() != null
            && v.getPrice() != null;
      }
    }

    return false;
  }

  public Boolean canDeleteVehicle(Vehicle vehicle, List<Vehicle> vehicleList) {
    if (vehicle != null && vehicleList != null) {
      int pos = VehicleUtils.findVehicleInListByLicensePlate(vehicle.getLicensePlate(), vehicleList);
      if (pos != -1) return vehicleList.get(pos) != null;
    }

    return false;
  }

  public Boolean validateLicensePlate(String licensePlate) {
    if (licensePlate != null && licensePlate.length() == 7) {
      for (int i = 0; i < licensePlate.length(); i++) {
        // letter
        if (i <= 2 || i == 4) {
          if (!(licensePlate.charAt(i) >= 'A' && licensePlate.charAt(i) <= 'Z')) {
            return false;
          }
        } else {
          // number
          if (!Character.isDigit(licensePlate.charAt(i))) {
            return false;
          }
        }
      }

      return true;
    }

    return false;
  }

  public Boolean isVehicleNil(Vehicle vehicle) {
    return !(vehicle != null
        && vehicle.getType() != null
        && vehicle.getLicensePlate() != null
        && vehicle.getModel() != null
        && vehicle.getPrice() != null
        && !vehicle.getLicensePlate().isBlank()
        && !vehicle.getModel().isBlank());
  }

  public Boolean isValidPrice(Float price) {
    if (price != null) {
      return price > 0.0f;
    }

    return false;
  }

  public Boolean isValidModel(String model) {
    if (model != null && !model.isBlank()) {
      for (char c : model.toCharArray()) {
        if (!(c >= 32 && c <= 126)) {
          return false;
        }
      }
    } else {
      return false;
    }

    return true;
  }

  public Boolean isValidVehicle(Vehicle vehicle) {
    return !this.isVehicleNil(vehicle)
        && this.validateLicensePlate(vehicle.getLicensePlate())
        && this.isValidPrice(vehicle.getPrice())
        && this.isValidModel(vehicle.getModel());
  }

}
