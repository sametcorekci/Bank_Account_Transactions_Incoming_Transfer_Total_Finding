package With_StreamAPI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

class StreamAPI_App {
    public static void main(String[] args) {
        String filePath = "CSV-PATH Gelmeli";
        String str_1 = "GELEN TRANSFER";
        String str_2 = "TL";
        List<String> totalSetString = new ArrayList<>();

        try (BufferedReader rd = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = rd.readLine()) != null) {
                // Stringleri temizleyip listeye ekliyoruz
                totalSetString.add(line.replaceAll(" ", "").replaceAll("\n", "").replace("\t", ""));
            }

            // Stream API ile "Gelen Transfer" içeren satırları buluyoruz, ardından sayıları çekiyoruz.
            List<Double> d_totalSetTutar = totalSetString.stream()
                    .filter(s -> s.contains(str_1))                // "GelenTransferFatihKılıç" içerenleri filtrele
                    .map(s -> s.substring(str_1.length(), s.indexOf(str_2)).trim())  // Tutarı çıkar
                    .map(s -> s.replace(".", "").replace(",", "."))
                    .map(Double::parseDouble)                      // String'ten Double'a çevir
                    .toList();                                     // List<Double> olarak topla

            // Stream ile toplam tutarı hesaplıyoruz.
            double toplamAtılan = d_totalSetTutar.stream().mapToDouble(Double::doubleValue).sum();
            System.out.printf("X Kişisinden Gelen Tutar : %.0f \n", toplamAtılan);

            // List<Double> içindeki verileri gösteriyoruz.
            System.out.println("---------------------------- List<Double> totalSetTutar içindeki veriler ----------------------------\n");
            d_totalSetTutar.forEach((s -> System.out.printf("Veri: %.0f \n", s)));

            // Spesifik aranılan tutar için... (Bonus)
            double aranTutar = 500;
            long sayac = d_totalSetTutar.stream()
                    .filter(t -> t == aranTutar)                   // Aranılan tutarları filtrele
                    .count();                                      // Kaç tane olduğunu say

            double arananTutarTotal = d_totalSetTutar.stream()
                    .filter(t -> t == aranTutar)                   // Aranılan tutarları filtrelenir
                    .mapToDouble(Double::doubleValue)              // DoubleStream'e çevirilir
                    .sum();                                        // Toplamı hesaplanır

            System.out.printf("Aranılan tutar: %.0f\nAradığım tutar adeti: %d\nToplam tutar: %.0f\n", aranTutar, sayac, arananTutarTotal);
            System.out.printf("With StreamPI");

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
