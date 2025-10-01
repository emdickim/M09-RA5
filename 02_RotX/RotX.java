/* AIXO SON DOS FUNCIONS PER XIFRAR I DESXIFRAR TEXTS EN ROT13 */
public class RotX {
    private static final char[] AbcMinisculas ="abcdefghijklmnopqrstuvwxyzàèéìíïòóùúüñç".toCharArray();

    private static final char[] AbcMayusculas ="ABCDEFGHIJKLMNOPQRSTUVWXYZÀÈÉÌÍÏÒÓÙÚÜÑÇ".toCharArray();
    public static String xifraRot13 (String text, int desplaçament) {
        String textXifrat = "";
        int posicio = 0;
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < AbcMinisculas.length; j++) {
                if (Character.isLowerCase(text.charAt(i))) {
                    if (text.charAt(i) == AbcMinisculas[j]) {
                        if (j + desplaçament < AbcMinisculas.length) {
                            textXifrat += AbcMinisculas[j + desplaçament] + "";   
                        } else {
                        textXifrat += AbcMinisculas[desplaçament - (AbcMinisculas.length % j )] + "";
                        }
                    }
                } else if (Character.isUpperCase(text.charAt(i))) {
                    if (text.charAt(i) == AbcMayusculas[j]) {
                            if (j + desplaçament < AbcMayusculas.length) {
                                textXifrat += AbcMayusculas[j + desplaçament] + "";
                                
                            } else {
                            textXifrat += AbcMayusculas[desplaçament - (AbcMayusculas.length % j )] + "";
                            }
                        }
                } else {
                    textXifrat += text.charAt(i);
                    break;
                }
                
            }
        }
        return textXifrat;
    }
    public static String desXifraRot13 (String text, int desplaçament) {

        String textDesxifrat = "";
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < AbcMinisculas.length; j++) {
                if (Character.isLowerCase(text.charAt(i))) {
                    if (text.charAt(i) == AbcMinisculas[j]) {
                        if (j - desplaçament >= 0) {
                            textDesxifrat += AbcMinisculas[j - desplaçament] + "";
                        }  else {
                            textDesxifrat += AbcMinisculas[AbcMinisculas.length - (desplaçament - j)] + "";
                        }
                    }
                } else if (Character.isUpperCase(text.charAt(i))) {
                    if (text.charAt(i) == AbcMayusculas[j]) {
                            if (j - desplaçament >= 0) {
                                textDesxifrat += AbcMayusculas[j - desplaçament] + "";
                                
                            } else {
                            textDesxifrat += AbcMayusculas[AbcMayusculas.length - (desplaçament - j)] + "";
                            }
                        }
                } else {
                    textDesxifrat += text.charAt(i);
                    break;
                }
            }
        }
        
        return textDesxifrat;
    }
    public static void forçaBrutaRotX (String cadenaXifrada) {
        String textDesxifrat = "";

        for (int i = 0; i < AbcMinisculas.length; i++) {
            System.out.println(i + " ->  "+ desXifraRot13(cadenaXifrada, i));
        }
        
    }
    public static void main(String[] args) {
        System.out.println("Ara xifrarem aquest text: Hola, Mr. calçot");
        String text = "Hola, Mr. calçot";
        System.out.println(xifraRot13(text, 13));
        text = xifraRot13(text, 4);
        System.out.println("Ara el desxifrarem");
        System.out.println(desXifraRot13(text, 13));
        forçaBrutaRotX(text);
    }
}
            /*for (int j = 0; j < cadenaXifrada.length(); j++) {
                for (int k = 0; k < AbcMinisculas.length; k++) 
                    if (Character.isLowerCase(cadenaXifrada.charAt(j))) {
                        if (cadenaXifrada.charAt(j) == AbcMinisculas[k]) {
                            if ((k-i) >= 0) {
                                textDesxifrat += AbcMinisculas[k-i] + "";
                            } else {
                                textDesxifrat += AbcMinisculas[AbcMinisculas.length % (i - k)];
                            } 
    
                        }
                    } else if (Character.isUpperCase(cadenaXifrada.charAt(j))) {
                    
                    } else {

                    }
                
            }
 *//* AIXO SON DOS FUNCIONS PER XIFRAR I DESXIFRAR TEXTS EN ROT13 */