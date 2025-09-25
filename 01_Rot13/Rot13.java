/* AIXO SON DOS FUNCIONS PER XIFRAR I DESXIFRAR TEXTS EN ROT13 */
public class Rot13 {
    private static final char[] AbcMinisculas ="abcdefghijklmnopqrstuvwxyzàèéìíïòóùúüñç".toCharArray();

    private static final char[] AbcMayusculas ="ABCDEFGHIJKLMNOPQRSTUVWXYZÀÈÉÌÍÏÒÓÙÚÜÑÇ".toCharArray();
    public static String xifraRot13 (String text) {
        String textXifrat = "";
        int posicio = 0;
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < AbcMinisculas.length; j++) {
                if (Character.isLowerCase(text.charAt(i))) {
                    if (text.charAt(i) == AbcMinisculas[j]) {
                        if (j+13 < AbcMinisculas.length) {
                            textXifrat += AbcMinisculas[j+13] + "";   
                        } else {
                        textXifrat += AbcMinisculas[13-(AbcMinisculas.length % j )] + "";
                        }
                    }
                } else if (Character.isUpperCase(text.charAt(i))) {
                    if (text.charAt(i) == AbcMayusculas[j]) {
                            if (j+13 < AbcMayusculas.length) {
                                textXifrat += AbcMayusculas[j + 13] + "";
                                
                            } else {
                            textXifrat += AbcMayusculas[13-(AbcMayusculas.length % j )] + "";
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
    public static String desXifraRot13 (String text) {

        String textDesxifrat = "";
        for (int i = 0; i < text.length(); i++) {
            for (int j = 0; j < AbcMinisculas.length; j++) {
                if (Character.isLowerCase(text.charAt(i))) {
                    if (text.charAt(i) == AbcMinisculas[j]) {
                        if (j - 13 >= 0) {
                            textDesxifrat += AbcMinisculas[j - 13] + "";
                        }  else {
                            textDesxifrat += AbcMinisculas[AbcMinisculas.length - (13 % j)] + "";
                        }
                    }
                } else if (Character.isUpperCase(text.charAt(i))) {
                    if (text.charAt(i) == AbcMayusculas[j]) {
                            if (j - 13 >= 0) {
                                textDesxifrat += AbcMayusculas[j-13] + "";
                                
                            } else {
                            textDesxifrat += AbcMayusculas[AbcMayusculas.length - (13 % j)] + "";
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
    public static void main(String[] args) {
        System.out.println("Ara xifrarem aquest text: Hola, Mr. calçot");
        String text = "Hola, Mr. calçot";
        System.out.println(xifraRot13(text));
        text = xifraRot13(text);
        System.out.println("Ara el desxifrarem");
        System.out.println(desXifraRot13(text));
    }
}