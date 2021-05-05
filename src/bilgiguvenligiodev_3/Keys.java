package bilgiguvenligiodev_3;

public class Keys {

    static String key, keysol = "", keysag = "", rkey;
    int dongu = 0;

    public void key_calistir() {
        dongu++;
        Kaydırma();
        RoundKey();
    }

    public String Ascii_Binary() {
        byte[] bytes = key.getBytes();
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

    public String Parity_Drop() {
        String newkey = "";
        int temp;
        for (int i = 0; i < 56; i++) {
            temp=Diziler.pardrp[i];
            newkey+=Ascii_Binary().charAt(temp-1);
        }
        return newkey;

    }

    public void Key_Parcala() {
        for (int i = 0; i < 28; i++) {
            keysol += Parity_Drop().charAt(i);
        }
        for (int i = 28; i < 56; i++) {
            keysag += Parity_Drop().charAt(i);
        }
    }

    public void Kaydırma() {
        char temp, temp1;
        if (dongu == 1 || dongu == 2 || dongu == 9 || dongu == 16) {
            temp = keysol.charAt(0);
            keysol += temp;
            keysol = keysol.substring(1, keysol.length());

            temp = keysag.charAt(0);
            keysag += temp;
            keysag = keysag.substring(1, keysag.length());
        } else {
            temp = keysol.charAt(0);
            temp1 = keysol.charAt(1);
            keysol += temp;
            keysol += temp1;
            keysol = keysol.substring(2, keysol.length());

            temp = keysag.charAt(0);
            temp1 = keysag.charAt(1);
            keysag += temp;
            keysag += temp1;
            keysag = keysag.substring(2, keysag.length());
        }
    }

    public String Birlesmiskeys() {
        return keysol + keysag;
    }

    public void RoundKey() {
        rkey = "";
        int temp;
        for (int i = 0; i < 48; i++) {
            temp = Diziler.D_Box[i];
            rkey += Birlesmiskeys().charAt(temp - 1);
        }
    }
}
