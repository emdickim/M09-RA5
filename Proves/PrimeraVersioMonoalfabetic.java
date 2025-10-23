/*Es parla de que un text es xifra amb xifratge mono-alfabètic quan per xifrar s’utilitza una
permutació de l'abecedari original i es fa una substitució de cada lletra de l’alfabet original per la
corresponent en la mateixa posició en l’alfabet permutat.
Crea un programa en Java anomenat Monoalfabetic.java que tingui un mètode
permutaAlfabet(alfabet), que generi una permutació de l’alfabet complet amb accents greus,
aguts, dièresi, «ç» i «ñ» i la retorni en un array de char. */

import java.util.*;

public class PrimeraVersioMonoalfabetic {
    private static final char[] alfabetOriginal = "ABCDEFGHIJKLMNOPQRSTUVWXYZÀÈÉÌÍÏÒÓÙÚÜÑÇ".toCharArray();
    private static final char[] alfabetPermutat = permutaAlfabet(alfabetOriginal.clone());
    
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

        return abc;
    }
    
    public static String monoAlfa(String paraula) {
        String nuevaPutaPalabra = "";
        //System.out.println("aixo de abaix es l'original");
       // System.out.println(paraula);
        for (int i = 0; i < paraula.length(); i++ ) {
            for (int j = 0; j < alfabetOriginal.length; j++) {
                if (Character.toUpperCase(paraula.charAt(i)) == alfabetOriginal[j]) {
                    //System.out.println(paraula.charAt(i) + "  " + i + "    " + alfabetOriginal[j] + " ES IGUAL A " + alfabetPermutat[j] + " EN LA POSICION " + j);
                    //break;
                    nuevaPutaPalabra += alfabetPermutat[j] + "";
                }
                if (!Character.isLetter(paraula.charAt(i))) {
                    nuevaPutaPalabra += paraula.charAt(i);
                    break;
                }
                
            }
        }
       //System.out.println(nuevaPutaPalabra + "XD");
        return nuevaPutaPalabra;
    }
    
    public static String desxifraAlfa(String paraula) {

        String paraulaDesxifrada = "";

        for (int i = 0; i < paraula.length(); i++) {
            for (int j = 0; j < alfabetPermutat.length; j++) {
                if (paraula.charAt(i) == alfabetPermutat[j]) {
                    paraulaDesxifrada += alfabetOriginal[j] + "";
                }
                if (!Character.isLetter(paraula.charAt(i))) {
                    paraulaDesxifrada += paraula.charAt(i);
                    break;
                }
            }
        }


        return paraulaDesxifrada;
    }
    public static void main(String[] args) {
        String[] frasesProves = {"ABC", "XYZ", "Hola, Mr. calçot", "Perdó, per tu què és?"};
        String[] frasesXifrades = new String[frasesProves.length];
        //monoAlfa("LOLXDJAHAHHAHAHAAHAHAHAHAHAHDGJSAHG");
        for (int i = 0; i < frasesProves.length; i++) {
            System.out.println("Paraula original: " + frasesProves[i]);
            System.out.println("Paraula xifrada: " + monoAlfa(frasesProves[i]));
            frasesXifrades[i] = monoAlfa(frasesProves[i]);
        }
        System.out.println();
        for (int i = 0; i < frasesXifrades.length; i++) {
            
            System.out.println("Paraula xifrada: " + frasesXifrades[i]);
            System.out.println("Paraula original desxifrada: " + desxifraAlfa(frasesXifrades[i]));
        }


        //String lol = monoAlfa("JAAAJAJAJAJAJAJAJJAJ");
      //  System.out.println(lol);
    }
}
