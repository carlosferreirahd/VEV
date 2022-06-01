package com.ufc.br.views;

import com.ufc.br.controllers.VehicleController;
import com.ufc.br.models.Vehicle;
import com.ufc.br.models.VehicleTypes;

import java.util.InputMismatchException;
import java.util.Scanner;

public class VehicleView {

  public static void showAllVehicles(VehicleController controller) {
    if (controller.getVehiclesList().isEmpty()) {
      System.out.println("Lista vazia...");
    } else {
      System.out.println("[");
      for (Vehicle vehicle : controller.getVehiclesList()) {
        System.out.println("\t{\n\t\t" + vehicle.toString() + "\n\t},");
      }
      System.out.println("]");
    }
  }

  public static Boolean addNewVehicle(VehicleController controller) {
    Scanner scanner = new Scanner(System.in);

    try {
      System.out.print("Entre com a placa do veiculo: ");
      String licensePlate = scanner.nextLine();

      System.out.print("Entre com o tipo do veiculo (1 - Carro / 2 - Moto): ");
      int typeInput = scanner.nextInt();
      scanner.nextLine();
      VehicleTypes type;

      if (typeInput == 1) {
        type = VehicleTypes.CAR;
      } else if (typeInput == 2) {
        type = VehicleTypes.MOTORCYCLE;
      } else {
        return false;
      }

      System.out.print("Entre com o modelo do veiculo: ");
      String model = scanner.nextLine();

      System.out.print("Entre com o preco do veiculo: ");
      Float price = scanner.nextFloat();

      return controller.addNewVehicle(type, licensePlate, model, price);

    } catch (InputMismatchException e) {
      System.out.println("Ops, um erro aconteceu...");
      return false;
    }
  }

  public static Boolean editVehicle(VehicleController controller) {
    Scanner scanner = new Scanner(System.in);

    try {
      System.out.print("Entre com a placa do veiculo que voce deseja editar: ");
      String licensePlate = scanner.nextLine();

      System.out.print("Entre com o NOVO tipo do veiculo (1 - Carro / 2 - Moto): ");
      int typeInput = scanner.nextInt();
      scanner.nextLine();
      VehicleTypes type;

      if (typeInput == 1) {
        type = VehicleTypes.CAR;
      } else if (typeInput == 2) {
        type = VehicleTypes.MOTORCYCLE;
      } else {
        return false;
      }

      System.out.print("Entre com o NOVO modelo do veiculo: ");
      String model = scanner.nextLine();

      System.out.print("Entre com o NOVO preco do veiculo: ");
      Float price = scanner.nextFloat();

      return controller.editVehicle(licensePlate, type, model, price);

    } catch (InputMismatchException e) {
      System.out.println("Ops, um erro aconteceu...");
      return false;
    }
  }

  public static Boolean deleteVehicle(VehicleController controller) {
    Scanner scanner = new Scanner(System.in);

    try {
      System.out.print("Entre com a placa do veiculo que voce deseja deletar do sistema: ");
      String licensePlate = scanner.nextLine();
      return controller.deleteVehicle(licensePlate);

    } catch (InputMismatchException e) {
      System.out.println("Ops, um erro aconteceu...");
      return false;
    }
  }

}
