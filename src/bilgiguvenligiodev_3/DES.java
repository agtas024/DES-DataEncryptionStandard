
package bilgiguvenligiodev_3;

public class DES {

    static String Li = "", Ri = "";
    static String[] sonuc = new String[new DuzMetin().Metin_bloklama_64bit().length];

    public void des_calistir() {
        LR();
        Keys k = new Keys();
        k.Parity_Drop();
        for (int i = 1; i <= 16; i++) {
            k.Key_Parcala();
            k.key_calistir();
            New_Ri_Li();

        }
        Sifreli_Metin_Bnry();
    }

    public void LR() {
        for (int i = 0; i < 32; i++) {
            Li += DuzMetin.permute.charAt(i);
        }
        for (int i = 32; i < 64; i++) {
            Ri += DuzMetin.permute.charAt(i);
        }
    }

    public String FRK() {
        int temp;
        String dizi[] = new String[8];
        String newRi = "", f = "", tempstr = "";
        for (int i = 0; i < 48; i++) {
            temp = Diziler.xD_box[i];
            newRi += Ri.charAt(temp - 1);
        }
        for (int i = 0; i < 48; i++) {
            f += Integer.toString((Integer.parseInt(newRi.charAt(i) + "") + Integer.parseInt(Keys.rkey.charAt(i) + "")) % 2);
        }
        for (int i = 0; i < dizi.length; i++) {
            for (int j = 0; j < 6; j++) {
                tempstr += f.charAt(i);
            }
            dizi[i] = tempstr;
        }
        for (int i = 0; i < dizi.length; i++) {
            String kontrol = dizi[i].charAt(0) + "";
            kontrol += dizi[i].charAt(5);
            String binaryString = dizi[i].charAt(1) + "";
            binaryString += dizi[i].charAt(2);
            binaryString += dizi[i].charAt(3);
            binaryString += dizi[i].charAt(4);
            int decimal = Integer.parseInt(binaryString, 2);
            int[][] d = SBox_Kontrol(i);
            if (kontrol == "00") {
                int deger = d[0][decimal];
                dizi[i] = Bit_Kontrol(Integer.toBinaryString(deger));
            } else if (kontrol == "01") {
                int deger = d[1][decimal];
                dizi[i] = Bit_Kontrol(Integer.toBinaryString(deger));
            } else if (kontrol == "10") {
                int deger = d[2][decimal];
                dizi[i] = Bit_Kontrol(Integer.toBinaryString(deger));
            } else if (kontrol == "11") {
                int deger = d[3][decimal];
                dizi[i] = Bit_Kontrol(Integer.toBinaryString(deger));
            }
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 4; j++) {
                tempstr += dizi[i].charAt(j);
            }
        }
        for (int i = 0; i < 32; i++) {
            temp = Diziler.sD_Box[i];
            f += tempstr.charAt(temp - 1);
        }
        return f;

    }

    public String LF_XOR() {
        String N = "";
        for (int i = 0; i < 32; i++) {
            N += Integer.toString((Integer.parseInt(Li.charAt(i) + "") + Integer.parseInt(FRK().charAt(i) + "")) % 2);
        }
        return N;
    }

    public void New_Ri_Li() {
        Li = Ri;
        Ri = LF_XOR();
    }

    public int[][] SBox_Kontrol(int i) {
        int dizi[][] = new int[4][16];
        if (i == 0) {
            dizi = Diziler.S_Box_1;
        } else if (i == 1) {
            dizi = Diziler.S_Box_2;
        } else if (i == 2) {
            dizi = Diziler.S_Box_3;
        } else if (i == 3) {
            dizi = Diziler.S_Box_4;
        } else if (i == 4) {
            dizi = Diziler.S_Box_5;
        } else if (i == 5) {
            dizi = Diziler.S_Box_6;
        } else if (i == 6) {
            dizi = Diziler.S_Box_7;
        } else if (i == 7) {
            dizi = Diziler.S_Box_8;
        }
        return dizi;
    }

    public String Bit_Kontrol(String bnry) {
        String b = "";
        if (bnry.length() == 1) {
            b += "000";
            b += bnry;
        } else if (bnry.length() == 2) {
            b += "00";
            b += bnry;
        } else if (bnry.length() == 3) {
            b += "0";
            b += bnry;
        } else if (bnry.length() == 4) {
            b += bnry;
        }
        return b;
    }

    public String FP() {
        int temp;
        String s = Li + Ri, s0 = "";
        for (int i = 0; i < s.length(); i++) {
            temp = Diziler.FinPer[i];
            s0 += s.charAt(temp - 1);
        }
        return s0;
    }

    public void Sifreli_Metin_Bnry() {
        sonuc[DuzMetin.i1] = FP();
    }
    public String ŞifreliMetin(){
        String sifreli="";
        for (int i = 0; i < sonuc.length; i++) {
            sifreli+=sonuc[i];
        }
        return sifreli;
    }
    public void Bin_to_Txt(){
    String str = "";

    for (int i = 0; i < ŞifreliMetin().length()/8; i++) {

        int a = Integer.parseInt(ŞifreliMetin().substring(8*i,(i+1)*8),2);
        str += (char)(a);
    }

    System.out.println(str);
    }
}
