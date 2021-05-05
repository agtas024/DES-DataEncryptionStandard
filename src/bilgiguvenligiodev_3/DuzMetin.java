package bilgiguvenligiodev_3;

public class DuzMetin {

    static String metin, permute = "";
    static int i1;
    
    public String Metin_Uzunlugu_Kontrol(String str){
        int kalan = (str.length()%64);
        String sss="";
        for (int i = 0; i < kalan; i++) {
            sss+="0";
        }
        sss+=str;
        return sss;
    }

    public String Ascii_Binary() {
        byte[] bytes = metin.getBytes();
        StringBuilder binary = new StringBuilder();
        for (byte b : bytes) {
            int val = b;
            for (int i = 0; i < 8; i++) {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
        }
        return binary.toString();
    }

    public String Hex_Binary_Ceviri(String hex_metin) {//bu metod 16 tabanlı stringi 2 tabanlı stringe dönüştürüyor
        String ikilik = "";
        String x = "";
        int Hexa;
        hex_metin = hex_metin.trim();
        hex_metin = hex_metin.replaceFirst("0x", "");

        for (int i = 0; i < hex_metin.length(); i++) {
            Hexa = Integer.parseInt("" + hex_metin.charAt(i), 16);
            x = Integer.toBinaryString(Hexa);

            while (x.length() < 4) {
                x = "0" + x;
            }
            ikilik += x;
        }
        return ikilik;
    }

    public void IP(String s) { //permutasyon metodu
        int temp;
        DES des = new DES();
        for (int t = 0; t < 64; t++) {
            temp = Diziler.InPer[t];
            permute += s.charAt(temp-1);
        }
        des.des_calistir();
    }

    public String[] Metin_bloklama_64bit() {
        String[] blok = new String[ Metin_Uzunlugu_Kontrol(Ascii_Binary()).length() / 64];
        int sayac = 0;
        for (int i = 0; i < blok.length; i++) {
            blok[i]="";
        }
        for (int i = 0; i <  Metin_Uzunlugu_Kontrol(Ascii_Binary()).length() / 64; i++) {
            for (int j = i * 64; j < (i * 64) + 64; j++) {
                blok[sayac] +=  Metin_Uzunlugu_Kontrol(Ascii_Binary()).charAt(j);
            }
            sayac++;
        }
        return blok;
    }

    public void Calistir() {
        for (i1 = 0; i1 < Metin_bloklama_64bit().length; i1++) {
            IP(Metin_bloklama_64bit()[i1]);
        }
        new DES().Bin_to_Txt();
    }
}
