package com.ufc.br;

import com.ufc.br.controllers.VehicleController;
import com.ufc.br.views.VehicleView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    VehicleController controller = new VehicleController();
    run(controller);
  }

  public static void run(VehicleController controller) {
    Scanner scanner = new Scanner(System.in);
    boolean running = true;

    while (running) {
      showMenu();

      try {
        int op = scanner.nextInt();

        switch (op) {
          case 1:
            VehicleView.showAllVehicles(controller);
            break;

          case 2:
            boolean inserted = VehicleView.addNewVehicle(controller);
            if (inserted) {
              System.out.println("Veiculo inserido com sucesso!");
            } else {
              System.out.println("Erro ao inserir veiculo...");
              System.out.println("Verifique se os dados inseridos estao corretos.");
            }
            break;

          case 3:
            boolean edited = VehicleView.editVehicle(controller);
            if (edited) {
              System.out.println("Veiculo editado com sucesso!");
            } else {
              System.out.println("Erro ao editar veiculo...");
              System.out.println("Verifique se os dados inseridos estao corretos.");
            }
            break;

          case 4:
            boolean deleted = VehicleView.deleteVehicle(controller);
            if (deleted) {
              System.out.println("Veiculo deletado com sucesso!");
            } else {
              System.out.println("Erro ao deletar veiculo");
              System.out.println("Verifique se os dados inseridos estao corretos.");
            }
            break;

          case 0:
            running = false;
            break;

          default:
            System.out.println("Opcao invalida!");
            break;
        }
      } catch (InputMismatchException e) {
        System.out.println("Entrada invalida!");
        scanner.nextLine();
      }
    }

    System.out.println("Saindo...");
    scanner.close();
  }
  public static void showMenu() {
    System.out.println("1 - Listar veiculos");
    System.out.println("2 - Cadastrar novo veiculo");
    System.out.println("3 - Editar veiculo");
    System.out.println("4 - Remover veiculo");
    System.out.println("0 - Sair");
    System.out.print("Entre com uma das opcoes >> ");
  }
}
