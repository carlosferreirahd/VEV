import com.ufc.br.models.Vehicle;
import com.ufc.br.models.VehicleTypes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
  Testes simples dos metodos get
*/

class VehicleTest {

  @Test
  void getType() {
    Vehicle v = new Vehicle(VehicleTypes.CAR, "ABC1D23", "HB20", 100.0f);
    assertEquals(VehicleTypes.CAR, v.getType());

    v.setType(VehicleTypes.MOTORCYCLE);
    assertEquals(VehicleTypes.MOTORCYCLE, v.getType());
  }

  @Test
  void getLicensePlate() {
    Vehicle v = new Vehicle(VehicleTypes.CAR, "ABC1D23", "Palio", 100.0f);
    assertEquals("ABC1D23", v.getLicensePlate());
  }

  @Test
  void getModel() {
    Vehicle v = new Vehicle(VehicleTypes.CAR, "ABC1D23", "Uno", 100.0f);
    assertEquals("Uno", v.getModel());
  }

  @Test
  void getPrice() {
    Vehicle v = new Vehicle(VehicleTypes.CAR, "ABC1D23", "Uno", 100.0f);
    assertEquals(100.0f, v.getPrice(), 0.1);
  }

}
