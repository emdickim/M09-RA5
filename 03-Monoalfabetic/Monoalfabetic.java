/*Es parla de que un text es xifra amb xifratge mono-alfabètic quan per xifrar s’utilitza una
permutació de l'abecedari original i es fa una substitució de cada lletra de l’alfabet original per la
corresponent en la mateixa posició en l’alfabet permutat.
Crea un programa en Java anomenat Monoalfabetic.java que tingui un mètode
permutaAlfabet(alfabet), que generi una permutació de l’alfabet complet amb accents greus,
aguts, dièresi, «ç» i «ñ» i la retorni en un array de char. */

import java.util.*;

public class Monoalfabetic {
    private static final char[] alfabetOriginal = "ABCDEFGHIJKLMNOPQRSTUVWXYZÀÈÉÌÍÏÒÓÙÚÜÑÇ".toCharArray();
    private static final char[] alfabetPermutat = permutaAlfabet(alfabetOriginal);
    
    public static char[] permutaAlfabet(char[] abc) {
        List<Character> remixAbcedari = new ArrayList<>();
        for (int i = 0; i < abc.length; i++) {
            remixAbcedari.add(abc[i]);  
        }
        System.out.println(remixAbcedari);
        Collections.shuffle(remixAbcedari);
        System.out.println(remixAbcedari);

        int index = 0;
        for (Character c : remixAbcedari) {
            abc[index] = c;
            index++;
        }
        
       // System.out.println(abc);
        return abc;
    }
    public static String monoAlfa(String paraula) {
        String nuevaPutaPalabra = "";
        System.out.println(alfabetPermutat);
        for (int i = 0; i < paraula.length(); i++ ) {
            for (int j = 0; j < alfabetOriginal.length; j++) {
                if (paraula.charAt(i) == alfabetPermutat[j]) {
                    nuevaPutaPalabra += alfabetOriginal[j] + "";
                }
            }
        }
       System.out.println(nuevaPutaPalabra + "XD");
        return nuevaPutaPalabra;
    }
    
    public String desxifraAlfa(String paraula) {
        return null;
    }
    public static void main(String[] args) {
        String[] frasesProves = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};
        monoAlfa("LOLXDJAHAHHAHAHAAHAHAHAHAHAHDGJSAHG");
        String lol = monoAlfa("JAAAJAJAJAJAJAJAJJAJ");
        System.out.println(lol);
    }
}
