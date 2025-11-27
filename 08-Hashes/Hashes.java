import java.security.*;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.HexFormat;

public class Hashes {

    public long npass = 0; // public perquè el main pugui fer h.npass

    // Charset EXACTE que demana l'enunciat
    private static final String CHARSET = "abcdeFABCDEF1234567890!";

    // Mètodes de hash reals
    public String getSHA512AmbSalt(String pw, String salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] hashBytes = md.digest((pw + salt).getBytes());
            HexFormat hex = HexFormat.of();
            return hex.formatHex(hashBytes);
        } catch (Exception e) {
            return "ERROR";
        }
    }

    public String getPBKDF2AmbSalt(String pw, String salt) {
        try {
            int iterations = 10000;
            int keyLength = 512;
            PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(), iterations, keyLength);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            byte[] hashBytes = skf.generateSecret(spec).getEncoded();
            HexFormat hex = HexFormat.of();
            return hex.formatHex(hashBytes);
        } catch (Exception e) {
            return "ERROR";
        }
    }

    // Força bruta simple i elegant amb recursió
    public String forcaBruta(String alg, String hashObjectiu, String salt) {
        npass = 0;

        for (int len = 1; len <= 6; len++) {
            String trobada = intentar("", len, alg, hashObjectiu, salt);
            if (trobada != null) {
                return trobada;
            }
        }
        return "No trobat";
    }

    private String intentar(String actual, int restant, String alg, String hashObjectiu, String salt) {
        if (restant == 0) {
            npass++; // Comptem cada intent

            String hashGenerat = alg.equals("SHA-512")
                ? getSHA512AmbSalt(actual, salt)
                : getPBKDF2AmbSalt(actual, salt);

            if (hashGenerat.equals(hashObjectiu)) {
                return actual; // TROBADA!
            }
            return null;
        }

        for (int i = 0; i < CHARSET.length(); i++) {
            char c = CHARSET.charAt(i);
            String resultat = intentar(actual + c, restant - 1, alg, hashObjectiu, salt);
            if (resultat != null) {
                return resultat;
            }
        }
        return null;
    }

    // Format del temps bonic
    public String getInterval(long t1, long t2) {
        long diff = t2 - t1;
        long segons = diff / 1000;
        long milis = diff % 1000;
        return segons + " segons i " + milis + " mil·lisegons";
    }

    
    public static void main(String[] args) throws Exception {
        String salt = "qpoweiraunñsslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();
        String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
            h.getPBKDF2AmbSalt(pw, salt)
        };
        String[] algorismes = { "SHA-512", "PBKDF2" };
        String pwTrobat = null;
        
        for (int i = 0; i < aHashes.length; i++) {
            System.out.printf("====================================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.printf("------------------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");
            
            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass: %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps:   %s\n", h.getInterval(t1, t2));
            System.out.printf("-------------------------------------\n\n");
        }
    }
}