package com.ufc.br.models;

/*
  Regras de negócio:

  1. Nenhum atributo pode ser vazio/nulo;

  2. licensePlate é a "chave primaria" de um veiculo, assim, não podem
  existir dois ou mais veículos com a mesma licensePlate;

  3. O atributo price deve ser > 0;

  4. O atributo licensePlate deve seguir o padrão Mercosul de placas, com
  quatro letras em caixa alta e três números, no formato `ABC1D23`

  5. O atributo licensePlate nao pode ser editado
 */

public class Vehicle {
  private VehicleTypes type;
  private String licensePlate;
  private String model;
  private Float price;

  public Vehicle() {

  }

  public Vehicle(VehicleTypes type, String licensePlate, String model, Float price) {
    this.type = type;
    this.licensePlate = licensePlate;
    this.model = model;
    this.price = price;
  }

  public VehicleTypes getType() {
    return type;
  }

  public void setType(VehicleTypes type) {
    this.type = type;
  }

  public String getLicensePlate() {
    return licensePlate;
  }

  public void setLicensePlate(String licensePlate) {
    this.licensePlate = licensePlate;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  @Override
  public String toString() {
    String vehicleString = this.type == VehicleTypes.CAR ? "Tipo: Carro; " : "Tipo: Moto; ";
    vehicleString += "Placa: " + this.licensePlate + "; ";
    vehicleString += "Modelo: " + this.model + "; ";
    vehicleString += "Preco: " + this.price + " }";
    return vehicleString;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (obj.getClass() != this.getClass()) {
      return false;
    }

    final Vehicle other = (Vehicle) obj;

    return other.getLicensePlate().equals(this.getLicensePlate());
  }
}
