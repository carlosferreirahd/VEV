import com.ufc.br.controllers.VehicleController;
import com.ufc.br.models.Vehicle;
import com.ufc.br.models.VehicleTypes;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleControllerTest {

  @Test
    // testes gerais de validacao passaram para nova classe `Validator`
  void testAddNewVehicleFunc_InvalidCase() {
    VehicleController controller = new VehicleController();
    assertEquals(false, controller.addNewVehicle(null, "ABC2D34", "Uno", 120f));
  }

  @Test
  void testAddNewVehicleFunc_ValidCase() {
    VehicleController controller = new VehicleController();
    assertEquals(true, controller.addNewVehicle(VehicleTypes.CAR, "ABC2D34", "Uno", 120f));
    assertEquals(true, controller.addNewVehicle(VehicleTypes.MOTORCYCLE, "HGF4S87", "Uno", 120f));
  }

  @Test
  void testEditVehicleFunc_InvalidCase() {
    VehicleController controller = new VehicleController();

    Vehicle v = new Vehicle(VehicleTypes.CAR, "GHJ4D28", "T-Cross", 362f);
    Vehicle v2 = new Vehicle(VehicleTypes.CAR, "GHJ4D22", "RX-8", 822f);
    Vehicle v3 = new Vehicle(VehicleTypes.MOTORCYCLE, "HDL1T33", "Pop 100", 674f);
    List<Vehicle> vehicleList = new ArrayList<Vehicle>();

    // `v` NAO esta na lista
    vehicleList.add(v2);
    vehicleList.add(v3);
    controller.setVehiclesList(vehicleList);

    // tentando editar `v`
    assertEquals(false, controller.editVehicle(v.getLicensePlate(), VehicleTypes.CAR, "Uno", 221f));
  }

  @Test
  void testEditVehicleFunc_ValidCase() {
    VehicleController controller = new VehicleController();

    Vehicle v = new Vehicle(VehicleTypes.CAR, "GHJ4D28", "T-Cross", 362f);
    Vehicle v2 = new Vehicle(VehicleTypes.CAR, "GHJ4D22", "RX-8", 822f);
    Vehicle v3 = new Vehicle(VehicleTypes.MOTORCYCLE, "HDL1T33", "Pop 100", 674f);
    List<Vehicle> vehicleList = new ArrayList<Vehicle>();

    // `v` esta na lista
    vehicleList.add(v);
    vehicleList.add(v2);
    vehicleList.add(v3);
    controller.setVehiclesList(vehicleList);

    // tentando editar `v`
    VehicleTypes newType = VehicleTypes.CAR;
    String newModel = "Uno";
    Float newPrice = 221f;
    assertEquals(true, controller.editVehicle(v.getLicensePlate(), newType, newModel, newPrice));

    // checando se `v` foi realmente editado
    boolean newValuesAreOk = v.getType() == newType
        && v.getModel().equals(newModel)
        && Objects.equals(v.getPrice(), newPrice);

    assertTrue(newValuesAreOk);
  }

  @Test
    // mesmo codigo do `testDeleteVehicleFunc_InvalidCase` (mesma logica)
  void testDeleteVehicleFunc_InvalidCase() {
    VehicleController controller = new VehicleController();

    Vehicle v = new Vehicle(VehicleTypes.CAR, "GHJ4D28", "T-Cross", 362f);
    Vehicle v2 = new Vehicle(VehicleTypes.CAR, "GHJ4D22", "RX-8", 822f);
    Vehicle v3 = new Vehicle(VehicleTypes.MOTORCYCLE, "HDL1T33", "Pop 100", 674f);
    List<Vehicle> vehicleList = new ArrayList<Vehicle>();

    // `v` NAO esta na lista
    vehicleList.add(v2);
    vehicleList.add(v3);
    controller.setVehiclesList(vehicleList);

    // tentando deletar `v`
    assertEquals(false, controller.deleteVehicle(v.getLicensePlate()));
  }

  @Test
  void testDeleteVehicleFunc_ValidCase() {
    VehicleController controller = new VehicleController();

    Vehicle v = new Vehicle(VehicleTypes.CAR, "GHJ4D28", "T-Cross", 362f);
    Vehicle v2 = new Vehicle(VehicleTypes.CAR, "GHJ4D22", "RX-8", 822f);
    Vehicle v3 = new Vehicle(VehicleTypes.MOTORCYCLE, "HDL1T33", "Pop 100", 674f);
    List<Vehicle> vehicleList = new ArrayList<Vehicle>();

    // `v` esta na lista
    vehicleList.add(v);
    vehicleList.add(v2);
    vehicleList.add(v3);
    controller.setVehiclesList(vehicleList);

    // tentando deletar `v` da lista do controller
    assertEquals(true, controller.deleteVehicle(v.getLicensePlate()));

    // checando se `v` foi realmente deletado
    boolean deletionIsOk = controller.getVehiclesList().size() == 2
        && !controller.getVehiclesList().get(0).equals(v)
        && !controller.getVehiclesList().get(1).equals(v);

    assertTrue(deletionIsOk);
  }
}
