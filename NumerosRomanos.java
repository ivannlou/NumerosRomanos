import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NumerosRomanos {

    public static int RomanoDecimal(String roman) {
        Map<Character, Integer> NumeroRomano = new HashMap<>();
        NumeroRomano.put('I', 1);
        NumeroRomano.put('V', 5);
        NumeroRomano.put('X', 10);
        NumeroRomano.put('L', 50);
        NumeroRomano.put('C', 100);
        NumeroRomano.put('D', 500);
        NumeroRomano.put('M', 1000);

        int decimal = 0;
        int prevValue = 0;
        for (int i = roman.length() - 1; i >= 0; i--) {
            int value = NumeroRomano.get(roman.charAt(i));
            if (value < prevValue) {
                decimal -= value;
            } else {
                decimal += value;
            }
            prevValue = value;
        }
        return decimal;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Ingrese numeros romanos como argumento en la linea de comandos");
            return;
        }

        try (FileWriter csvWriter = new FileWriter("RomanoDecimal.csv")) {
            csvWriter.append("Numero Romano");
            csvWriter.append(",");
            csvWriter.append("Numero Decimal");
            csvWriter.append("\n");

            for (String roman : args) {
                int decimal = RomanoDecimal(roman);
                csvWriter.append(roman);
                csvWriter.append(",");
                csvWriter.append(String.valueOf(decimal));
                csvWriter.append("\n");
                System.out.println("Numero Romano: " + roman + " = Numero Decimal: " + decimal);
            }

            System.out.println("Archivo generado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al generar el archivo CSV: " + e.getMessage());
        }
    }
}
