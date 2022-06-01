import com.ufc.br.models.Vehicle;
import com.ufc.br.models.VehicleTypes;
import com.ufc.br.utils.VehicleUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleUtilsTest {

  @Test
  void testFindVehicleInListByLicensePlateFunc_NullLicensePlateCase() {
    assertEquals(-1, VehicleUtils.findVehicleInListByLicensePlate(null, new ArrayList<Vehicle>()));
  }

  @Test
  void testFindVehicleInListByLicensePlateFunc_NullVehicleListCase() {
    assertEquals(-1, VehicleUtils.findVehicleInListByLicensePlate("ABC2D64", null));
  }

  @Test
  void testFindVehicleInListByLicensePlateFunc_LicensePlateNotFoundCase() {
    List<Vehicle> vehicleList = new ArrayList<Vehicle>();

    vehicleList.add(new Vehicle(VehicleTypes.CAR, "HGF4H87", "Uno", 120f));
    vehicleList.add(new Vehicle(VehicleTypes.MOTORCYCLE, "KJH5P58", "Pop 100", 540f));
    vehicleList.add(new Vehicle(VehicleTypes.CAR, "WER5B11", "HB20", 612f));

    assertEquals(-1, VehicleUtils.findVehicleInListByLicensePlate("MNZ5V43", vehicleList));
  }

  @Test
  void testFindVehicleInListByLicensePlateFunc_LicensePlateFoundCase() {
    List<Vehicle> vehicleList = new ArrayList<Vehicle>();

    // pos 0
    vehicleList.add(new Vehicle(VehicleTypes.CAR, "HGF4H87", "Uno", 120f));

    // pos 1
    vehicleList.add(new Vehicle(VehicleTypes.MOTORCYCLE, "KJH5P58", "Pop 100", 540f));

    // pos 2
    vehicleList.add(new Vehicle(VehicleTypes.CAR, "WER5B11", "HB20", 612f));

    assertEquals(0, VehicleUtils.findVehicleInListByLicensePlate("HGF4H87", vehicleList));
    assertEquals(1, VehicleUtils.findVehicleInListByLicensePlate("KJH5P58", vehicleList));
    assertEquals(2, VehicleUtils.findVehicleInListByLicensePlate("WER5B11", vehicleList));
  }
}
