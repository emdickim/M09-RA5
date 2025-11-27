

public class hashes2 {
     // 1. Variable para contar intentos (accesible desde main como h.npass)
    public long npass = 0; 
    
    // 2. Definimos los caracteres posibles.
    // IMPORTANTE: Debe incluir los caracteres de la contraseña objetivo "aaabF!"
    private static final String CHARSET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#";

    public String getSHA512AmbSalt(String msg, String salt) {
        return msg + salt + "SHA-512";
    }

    public String getPBKDF2AmbSalt(String msg, String salt) {
        return msg + salt + "PBKDF2";
    }

    // 3. Método principal de fuerza bruta
    public String forcaBruta(String alg, String hash, String salt) {
        npass = 0; // Reiniciar contador
        
        // Probamos longitudes de contraseña desde 1 hasta 6 caracteres (o más)
        // Como sabemos que la pw es "aaabF!" (6 chars), el bucle llegará a i=6
        for (int i = 1; i <= 6; i++) {
            // Llamamos a la función recursiva para generar combinaciones de longitud 'i'
            String result = attemptRecursively("", i, alg, hash, salt);
            if (result != null) {
                return result; // ¡Encontrada!
            }
        }
        return "No encontrada";
    }

    // 4. Método ayudante recursivo
    private String attemptRecursively(String currentAttempt, int length, String alg, String hash, String salt) {
        // Caso base: si la longitud actual es la deseada, comprobamos el hash
        if (length == 0) {
            npass++;
            
            // Generamos el hash simulado según el algoritmo pedido
            String generatedHash;
            if (alg.equals("SHA-512")) {
                generatedHash = getSHA512AmbSalt(currentAttempt, salt);
            } else {
                generatedHash = getPBKDF2AmbSalt(currentAttempt, salt);
            }

            // Comparamos
            if (generatedHash.equals(hash)) {
                return currentAttempt; // Contraseña encontrada
            }
            return null; // No coincide
        }

        // Paso recursivo: Iterar por cada carácter del CHARSET
        for (int i = 0; i < CHARSET.length(); i++) {
            // Añadir carácter y llamar de nuevo reduciendo la longitud restante
            String result = attemptRecursively(currentAttempt + CHARSET.charAt(i), length - 1, alg, hash, salt);
            if (result != null) return result; // Si se encontró en la profundidad, devolverla
        }

        return null;
    }
    
    public String getInterval(long t1, long t2) {
        long diff = t2 - t1;
        return diff + " ms";
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
