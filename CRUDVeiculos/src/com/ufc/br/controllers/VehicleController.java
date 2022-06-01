package com.ufc.br.controllers;

import com.ufc.br.models.Vehicle;
import com.ufc.br.models.VehicleTypes;
import com.ufc.br.utils.Validator;
import com.ufc.br.utils.VehicleUtils;

import java.util.ArrayList;
import java.util.List;

public class VehicleController {
  private List<Vehicle> vehiclesList;
  private Validator validator;

  public VehicleController() {
    this.vehiclesList = new ArrayList<Vehicle>();
    this.validator = new Validator();
  }

  public void setVehiclesList(List<Vehicle> vehicleList) {
    this.vehiclesList = vehicleList;
  }

  public List<Vehicle> getVehiclesList() {
    return this.vehiclesList;
  }

  public Boolean addNewVehicle(VehicleTypes type, String licensePlate, String model, Float price) {
    Vehicle newVehicle = new Vehicle(type, licensePlate.trim(), model.trim(), price);

    boolean canInsert = validator.canInsertNewVehicle(newVehicle, vehiclesList);

    if (!canInsert) return false;

    return vehiclesList.add(newVehicle);
  }

  public Boolean editVehicle(String licensePlate, VehicleTypes newType, String newModel, Float newPrice) {
    Vehicle tmp = new Vehicle(newType, licensePlate, newModel, newPrice);

    if (validator.canEditVehicle(tmp, this.vehiclesList)) {
      int pos = VehicleUtils.findVehicleInListByLicensePlate(licensePlate, this.vehiclesList);

      if (pos != -1) {
        this.vehiclesList.get(pos).setType(newType);
        this.vehiclesList.get(pos).setModel(newModel.trim());
        this.vehiclesList.get(pos).setPrice(newPrice);
        return true;
      }
    }

    return false;
  }

  public Boolean deleteVehicle(String licensePlate) {
    Vehicle tmp = new Vehicle();
    tmp.setLicensePlate(licensePlate);

    if (validator.canDeleteVehicle(tmp, this.vehiclesList)) {
      int pos = VehicleUtils.findVehicleInListByLicensePlate(licensePlate, this.vehiclesList);
      if (pos != -1) {
        this.vehiclesList.remove(pos);
        return true;
      }
    }

    return false;
  }
}
