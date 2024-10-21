package Without_StreamAPI;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dont_StreamAPI_App {
    public static void main(String[] args) {
        String filePath = "CSV-PATH Gelmeli";
        String str_1 = "GELEN TRANSFER";
        String str_2 = "TL";
        List<String> totalSetString = new ArrayList<>();
        List<Double> d_totalSetTutar = new ArrayList<>();



        try (BufferedReader rd = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 1;
            while ((line = rd.readLine()) != null) {
                totalSetString.add(line.replaceAll(" ", "").replaceAll("\n","").replace("\t",""));
            }
            //------------------------------------------------------------------------------------------------------------------
            // List <String> içindeki verileri double türüne çevirip daha sonra  List<Double> ' a ekledim.
            for (String s : totalSetString) {
                if (s.contains(str_1)) {
                    // Tutarı çıkar
                    String stringMony = s.substring(str_1.length(), s.indexOf(str_2)).trim();
                    // Binlik ayırıcıları kaldır ve ondalık ayırıcı olarak virgülü nokta ile değiştir
                    // xx.yyy,zz  -->   xxyyy,zz --> xxyyy.zz
                    stringMony = stringMony.replace(".", "").replace(",", ".");
                    d_totalSetTutar.add(Double.parseDouble(stringMony));
                }
            }
            //------------------------------------------------------------------------------------------------------------------
            //------------------------------------------------------------------------------------------------------------------

            // List<Double> içindeki veriler gösteriliyor
            int forRange = 1;
            System.out.println("---------------------------- List<Double> totalSetTutar içindeki veriler ----------------------------\n");
            for (double s : d_totalSetTutar) {
                System.out.printf("%d.Veri: %.0f \n", forRange, s);
                forRange++;
            }
            //------------------------------------------------------------------------------------------------------------------
            //------------------------------------------------------------------------------------------------------------------

            //List<Double> içindeli toplam tutar gösteriliyor.
            double toplamAtılan=0;
            for (Double aDouble : d_totalSetTutar) {
                toplamAtılan = toplamAtılan + aDouble;
            }
            System.out.printf("Gelen Transferden Gelen toplam tutar : %.0f \n",toplamAtılan);
            //------------------------------------------------------------------------------------------------------------------
            //------------------------------------------------------------------------------------------------------------------
            //Spesifik aranılmak istenilen tutar için opsioel...
            double aranTutar=500;
            int sayac=0;
            double arananTutarTotal=0;
            for(int i=0; i<d_totalSetTutar.size();i++)
            {
                if(d_totalSetTutar.get(i) == aranTutar)
                {
                    arananTutarTotal = arananTutarTotal + d_totalSetTutar.get(i);
                    sayac++;
                }
            }
            System.out.printf("Aranılan tutar : %.0f\nAradıgım tutar adeti : %d\nToplam tutar : %.0f\n",aranTutar,sayac,arananTutarTotal);
            //------------------------------------------------------------------------------------------------------------------
            System.out.println("WithOUT StreamAPI");


        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
