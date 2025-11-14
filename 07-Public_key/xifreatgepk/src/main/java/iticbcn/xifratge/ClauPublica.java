package iticbcn.xifratge;

import java.security.*;
import javax.crypto.Cipher;

public class ClauPublica {

    // Genera un par de claves RSA (pública + privada)
    public KeyPair generaParrellClausRSA() throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);  // Tamaño recomendado
        return kpg.generateKeyPair();
    }

    // Cifra un mensaje usando la clave pública
    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, clauPublica);
        return cipher.doFinal(msg.getBytes("UTF-8"));
    }

    // Descifra un mensaje con la clave privada
    public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, clauPrivada);
        byte[] desencriptat = cipher.doFinal(msgXifrat);
        return new String(desencriptat, "UTF-8");
    }
}