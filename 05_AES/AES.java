import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;

public class AES {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguis";

    public static byte[] xifraAES(String msg, String clau) throws Exception {
        // Obtenir els bytes de l'String
        byte[] bMsg = msg.getBytes("UTF-8");

        // Genera IvParameterSpec (IV aleatori)
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Genera hash de la clau (SHA-256 → 256 bits)
        MessageDigest sha = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] key = sha.digest(clau.getBytes("UTF-8"));
        key = Arrays.copyOf(key, 16); // només 128 bits per AES-128

        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORISME_XIFRAT);

        // Encrypt
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        byte[] xifrat = cipher.doFinal(bMsg);

        // Combinar IV i part xifrada (concatenació)
        byte[] resultat = new byte[iv.length + xifrat.length];
        System.arraycopy(iv, 0, resultat, 0, iv.length);
        System.arraycopy(xifrat, 0, resultat, iv.length, xifrat.length);

        return resultat;
    }

    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) {
        try {
            // Extreure l'IV
            byte[] iv = Arrays.copyOfRange(bIvIMsgXifrat, 0, MIDA_IV);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            // Extreure la part xifrada
            byte[] msgXifrat = Arrays.copyOfRange(bIvIMsgXifrat, MIDA_IV, bIvIMsgXifrat.length);

            // Fer hash de la clau
            MessageDigest sha = MessageDigest.getInstance(ALGORISME_HASH);
            byte[] key = sha.digest(clau.getBytes("UTF-8"));
            key = Arrays.copyOf(key, 16); // 128 bits

            SecretKeySpec secretKey = new SecretKeySpec(key, ALGORISME_XIFRAT);

            // Desxifrar
            Cipher cipher = Cipher.getInstance(FORMAT_AES);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
            byte[] desxifrat = cipher.doFinal(msgXifrat);

            // return String desxifrat
            return new String(desxifrat, "UTF-8");

        } catch (Exception e) {
            System.err.println("Error de desxifrat: " + e.getLocalizedMessage());
            return "";
        }
    }

    public static void main(String[] args) {
        String msgs[] = {
            "Lorem ipsum dicet",
            "Hola Andrés cómo está tu cuñado",
            "Àgora Ïlla Ótto"
        };

        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i];

            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            }

            System.out.println("-------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc (Base64): " + java.util.Base64.getEncoder().encodeToString(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }
}
