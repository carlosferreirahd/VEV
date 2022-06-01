import com.ufc.br.models.Vehicle;
import com.ufc.br.models.VehicleTypes;
import com.ufc.br.utils.Validator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ValidatorTest {

  @Test
  void testIsVehicleNilFunc_NullCase() {
    Validator validator = new Validator();
    assertEquals(true, validator.isVehicleNil(null));
  }

  @Test
  void testIsVehicleNilFunc_NullVehicleTypeCase() {
    Validator validator = new Validator();
    Vehicle v = new Vehicle(null, "ABC1D23", "Onix", 100.0f);
    assertEquals(true, validator.isVehicleNil(v));
  }

  @Test
  void testIsVehicleNilFunc_InvalidLicensePlateCase() {
    Validator validator = new Validator();

    // caso licensePlate null
    Vehicle v = new Vehicle(VehicleTypes.CAR, null, "Uno", 100.0f);
    assertEquals(true, validator.isVehicleNil(v));

    // caso licensePlate string vazia
    v = new Vehicle(VehicleTypes.CAR, "", "HB20", 100.0f);
    assertEquals(true, validator.isVehicleNil(v));

    // caso licensePlate composta apenas por espacos
    v = new Vehicle(VehicleTypes.CAR, "      ", "T-Cross", 100.0f);
    assertEquals(true, validator.isVehicleNil(v));
  }

  @Test
  void testIsVehicleNilFunc_InvalidModelCase() {
    Validator validator = new Validator();

    // caso model null
    Vehicle v = new Vehicle(VehicleTypes.CAR, "ABC1D23", null, 100.0f);
    assertEquals(true, validator.isVehicleNil(v));

    // caso model string vazia
    v = new Vehicle(VehicleTypes.CAR, "ABC1D23", "", 100.0f);
    assertEquals(true, validator.isVehicleNil(v));

    // caso model composta apenas por espacos
    v = new Vehicle(VehicleTypes.CAR, "ABC1D23", "     ", 100.0f);
    assertEquals(true, validator.isVehicleNil(v));
  }

  @Test
  void testIsVehicleNilFunc_NullPriceCase() {
    Validator validator = new Validator();
    Vehicle v = new Vehicle(VehicleTypes.MOTORCYCLE, "ABC1D23", "Yamaha", null);
    assertEquals(true, validator.isVehicleNil(v));
  }

  @Test
  void testIsVehicleNilFunc_NotNilCase() {
    Validator validator = new Validator();
    Vehicle v = new Vehicle(VehicleTypes.CAR, "ABC1D23", "Palio", 100.0f);
    assertEquals(false, validator.isVehicleNil(v));
  }

  @Test
  void testIsValidPriceFunc_NullCase() {
    Validator validator = new Validator();
    assertEquals(false, validator.isValidPrice(null));
  }

  @Test
  void testIsValidPriceFunc_InvalidPriceCase() {
    Validator validator = new Validator();

    // caso price == 0
    assertEquals(false, validator.isValidPrice(0f));

    // caso price < 0
    assertEquals(false, validator.isValidPrice(-100f));

    // caso valor no extremo
    assertEquals(false, validator.isValidPrice(-0.000001f));
  }

  @Test
  void testIsValidPriceFunc_ValidPriceCase() {
    Validator validator = new Validator();

    // caso price > 0
    assertEquals(true, validator.isValidPrice(100f));

    // caso valor no extremo
    assertEquals(true, validator.isValidPrice(0.000001f));
  }

  @Test
  void testIsValidModelFunc_InvalidModelCase() {
    Validator validator = new Validator();

    // caso model null
    assertEquals(false, validator.isValidModel(null));

    // caso model string vazia
    assertEquals(false, validator.isValidModel(""));

    // caso model apenas espacos
    assertEquals(false, validator.isValidModel("   "));

    // caso caracteres extrangeiros
    assertEquals(false, validator.isValidModel("ББББ"));

    // caso emojis
    assertEquals(false, validator.isValidModel("\uD83D\uDE07\uD83D\uDE07\uD83D\uDE07\uD83D\uDE07"));
  }

  @Test
  void testIsValidModelFunc_ValidModelCase() {
    Validator validator = new Validator();
    assertEquals(true, validator.isValidModel("Uno"));
    assertEquals(true, validator.isValidModel("Pop 100")); // numeros ok
    assertEquals(true, validator.isValidModel("X-Cross")); // caractere especial ok
  }

  @Test
  void testValidateLicensePlateFunc_InvalidLicensePlateCase() {
    Validator validator = new Validator();

    // caso licensePlate null
    assertEquals(false, validator.validateLicensePlate(null));

    // caso licensePlate string vazia
    assertEquals(false, validator.validateLicensePlate(""));

    // caso licensePlate string com apenas espacos
    assertEquals(false, validator.validateLicensePlate("       "));

    // caso licensePlate fora do padrao definido nas regras de negocio
    String[] invalidLicensePlates = {
        "aBC1D23", // caractere caixa baixa em uma posicao
        "AbC1D23", // caractere caixa baixa em uma posicao
        "ABc1D23", // caractere caixa baixa em uma posicao
        "ABC1d23", // caractere caixa baixa em uma posicao
        "abc1d23", // caractere caixa baixa em todas posicoes
        "ABCDEFG", // apenas caracteres caixa alta
        "abcdefg", // apenas caracteres caixa baixa
        "1234567", // apenas numeros
        "ABC1", // tamanho menor que o padrao
        "ABC1D23FG", // tamanho maior que o padrao
        "123A4BC", // letras trocadas por numeros
        "@%S23F66", // caracteres especiais no lugar das letras
        "DFG&J$!", // caracteres especiais no lugar dos numeros
        "\uD83D\uDE07\uD83D\uDE07\uD83D\uDE07\uD83D\uDE07\uD83D\uDE07\uD83D\uDE07\uD83D\uDE07", // 7 emojis
        "\uD83D\uDE05\uD83D\uDE05\uD83D\uDE051\uD83D\uDE0523", // emojis no lugar das letras
        "ABC\uD83D\uDE05D\uD83D\uDE05\uD83D\uDE05", // emojis no lugar dos numeros
        "БББББББ", // string em alfabeto extrangeiro
        "БББ1Б23", // string quase valida (caracteres em caixa alta + numero no lugar certo), mas em outro alfabeto
    };

    for (String licensePlate : invalidLicensePlates) {
      assertEquals(false, validator.validateLicensePlate(licensePlate));
    }
  }

  @Test
  void testValidateLicensePlateFunc_ValidLicensePlateCase() {
    Validator validator = new Validator();
    assertEquals(true, validator.validateLicensePlate("GDJ5T89"));
  }

  @Test
  void testIsValidVehicleFunc_InvalidVehicleCase() {
    Validator validator = new Validator();

    // caso null
    assertEquals(false, validator.isValidVehicle(null));

    // caso atributo invalido
    Vehicle v = new Vehicle(VehicleTypes.MOTORCYCLE, "ABC1DF3", "", 120f);
    assertEquals(false, validator.isValidVehicle(v));
  }

  @Test
  void testIsValidVehicleFunc_ValidVehicleCase() {
    Validator validator = new Validator();

    Vehicle v = new Vehicle(VehicleTypes.MOTORCYCLE, "ABC1D23", "Versys", 120f);

    assertEquals(true, validator.isValidVehicle(v));
  }

  @Test
  void testCanInsertNewVehicleFunc_InvalidCase() {
    Validator validator = new Validator();

    // caso veiculo nulo
    assertEquals(false, validator.canInsertNewVehicle(null, new ArrayList<Vehicle>()));

    // caso lista nula
    Vehicle v = new Vehicle(VehicleTypes.CAR, "FGD2F73", "Fox", 120f);
    assertEquals(false, validator.canInsertNewVehicle(v, null));

    // caso veiculo invalido
    v = new Vehicle(VehicleTypes.CAR, "FGD2F3", "Palio", -23f);
    assertEquals(false, validator.canInsertNewVehicle(v, new ArrayList<Vehicle>()));

    // caso veiculo com mesma licensePlate ja dentro da lista
    v = new Vehicle(VehicleTypes.CAR, "FGD2F36", "HB20", 232f);
    Vehicle v2 = new Vehicle(VehicleTypes.CAR, "FGD2F36", "Uno", 111f);
    List<Vehicle> vehicleList = new ArrayList<Vehicle>();
    vehicleList.add(v2);
    assertEquals(false, validator.canInsertNewVehicle(v, vehicleList));
  }

  @Test
  void testCanInsertNewVehicleFunc_ValidCase() {
    Validator validator = new Validator();

    // caso veiculo valido
    Vehicle v = new Vehicle(VehicleTypes.CAR, "GHJ4D28", "T-Cross", 362f);
    assertEquals(true, validator.canInsertNewVehicle(v, new ArrayList<Vehicle>()));

    // caso inserir com lista ja preenchida
    Vehicle v2 = new Vehicle(VehicleTypes.CAR, "GHJ4D22", "RX-8", 822f);
    Vehicle v3 = new Vehicle(VehicleTypes.MOTORCYCLE, "HDL1T33", "Pop 100", 674f);
    List<Vehicle> vehicleList = new ArrayList<Vehicle>();
    vehicleList.add(v2);
    vehicleList.add(v3);
    Vehicle v4 = new Vehicle(VehicleTypes.MOTORCYCLE, "DKI2B76", "Harley Iron", 231f);
    assertEquals(true, validator.canInsertNewVehicle(v4, vehicleList));
  }

  @Test
  void testCanEditVehicleFunc_InvalidCase() {
    Validator validator = new Validator();

    // caso veiculo nulo
    assertEquals(false, validator.canEditVehicle(null, new ArrayList<Vehicle>()));

    // caso lista nula
    Vehicle v = new Vehicle(VehicleTypes.MOTORCYCLE, "GHF3D67", "Pop 100", 321f);
    assertEquals(false, validator.canEditVehicle(v, null));

    // caso veiculo nao esta na lista
    Vehicle v2 = new Vehicle(VehicleTypes.CAR, "FGH5H77", "Uno", 523f);
    Vehicle v3 = new Vehicle(VehicleTypes.MOTORCYCLE, "KLG5T88", "Yamaha Kawasaki", 143f);
    List<Vehicle> vehicleList = new ArrayList<Vehicle>();
    vehicleList.add(v2);
    vehicleList.add(v3);
    assertEquals(false, validator.canEditVehicle(v, vehicleList));
  }

  @Test
  void testCanEditVehicleFunc_ValidCase() {
    Validator validator = new Validator();

    // caso veiculo na lista
    Vehicle v = new Vehicle(VehicleTypes.MOTORCYCLE, "GHF3D67", "Pop 100", 321f);
    Vehicle v2 = new Vehicle(VehicleTypes.CAR, "FGH5H77", "Uno", 523f);
    Vehicle v3 = new Vehicle(VehicleTypes.MOTORCYCLE, "KLG5T88", "Yamaha Kawasaki", 143f);
    List<Vehicle> vehicleList = new ArrayList<Vehicle>();
    vehicleList.add(v);
    vehicleList.add(v2);
    vehicleList.add(v3);

    // veiculo igual ao veiculo `v`
    Vehicle v4 = new Vehicle(VehicleTypes.MOTORCYCLE, "GHF3D67", "Pop 100", 321f);

    assertEquals(true, validator.canEditVehicle(v4, vehicleList));
  }

  @Test // exatamente o mesmo codigo do `testCanEditVehicleFunc_InvalidCase` (mesma logica)
  void testCanDeleteVehicleFunc_InvalidCase() {
    Validator validator = new Validator();

    // caso veiculo nulo
    assertEquals(false, validator.canDeleteVehicle(null, new ArrayList<Vehicle>()));

    // caso lista nula
    Vehicle v = new Vehicle(VehicleTypes.MOTORCYCLE, "GHF3D67", "Pop 100", 321f);
    assertEquals(false, validator.canDeleteVehicle(v, null));

    // caso veiculo nao esta na lista
    Vehicle v2 = new Vehicle(VehicleTypes.CAR, "FGH5H77", "Uno", 523f);
    Vehicle v3 = new Vehicle(VehicleTypes.MOTORCYCLE, "KLG5T88", "Yamaha Kawasaki", 143f);
    List<Vehicle> vehicleList = new ArrayList<Vehicle>();
    vehicleList.add(v2);
    vehicleList.add(v3);
    assertEquals(false, validator.canDeleteVehicle(v, vehicleList));
  }

  @Test // exatamente o mesmo codigo do `testCanEditVehicleFunc_ValidCase` (mesma logica)
  void testCanDeleteVehicleFunc_ValidCase() {
    Validator validator = new Validator();

    // caso veiculo na lista
    Vehicle v = new Vehicle(VehicleTypes.MOTORCYCLE, "GHF3D67", "Pop 100", 321f);
    Vehicle v2 = new Vehicle(VehicleTypes.CAR, "FGH5H77", "Uno", 523f);
    Vehicle v3 = new Vehicle(VehicleTypes.MOTORCYCLE, "KLG5T88", "Yamaha Kawasaki", 143f);
    List<Vehicle> vehicleList = new ArrayList<Vehicle>();
    vehicleList.add(v);
    vehicleList.add(v2);
    vehicleList.add(v3);

    // veiculo igual ao veiculo `v`
    Vehicle v4 = new Vehicle(VehicleTypes.MOTORCYCLE, "GHF3D67", "Pop 100", 321f);

    assertEquals(true, validator.canDeleteVehicle(v4, vehicleList));
  }
}
