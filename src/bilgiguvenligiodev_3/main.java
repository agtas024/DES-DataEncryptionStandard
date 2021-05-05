package bilgiguvenligiodev_3;

public class main {

    public static void main(String[] args) {
        Keys k = new Keys();
        DuzMetin d = new DuzMetin();

        String _64_bit_key = "abcdefgh";
        k.key = _64_bit_key;

        d.metin = "gfgsnghsg gh sgh gsh g hdfghs";
        d.Calistir();
        for (int i = 0; i < DES.sonuc.length; i++) {
            System.out.println(DES.sonuc[i]);
        }
    }
}
