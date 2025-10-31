/*Es parla de que un text es xifra amb xifratge mono-alfabètic quan per xifrar s’utilitza una
permutació de l'abecedari original i es fa una substitució de cada lletra de l’alfabet original per la
corresponent en la mateixa posició en l’alfabet permutat.
Crea un programa en Java anomenat Monoalfabetic.java que tingui un mètode
permutaAlfabet(alfabet), que generi una permutació de l’alfabet complet amb accents greus,
aguts, dièresi, «ç» i «ñ» i la retorni en un array de char. */

package iticbcn.xifratge;

import java.util.*;

public class XifradorMonoalfabetic {
    private static final char[] alfabetOriginal = "ABCDEFGHIJKLMNOPQRSTUVWXYZÀÈÉÌÍÏÒÓÙÚÜÑÇ".toCharArray();
    private static final char[] alfabetPermutat = permutaAlfabet(alfabetOriginal.clone());
    public static char[] permutaAlfabet(char[] abc) {
        List<Character> remixAbcedari = new ArrayList<>();

        for (int i = 0; i < abc.length; i++) {
            remixAbcedari.add(abc[i]);
        }

        Collections.shuffle(remixAbcedari);

        for (int i = 0; i < abc.length; i++) {
            abc[i] = remixAbcedari.get(i);
        }

        return abc;
    }

    
    
    public static String monoAlfa(String paraula) {
        String novaParaula = "";
        for (int i = 0; i < paraula.length(); i++) {
            char c = paraula.charAt(i);
            char maj = Character.toUpperCase(c);
            boolean trobat = false;

            for (int j = 0; j < alfabetOriginal.length; j++) {
                if (maj == alfabetOriginal[j]) {
                    char substitut = alfabetPermutat[j];
                    if (Character.isLowerCase(c)) {
                        substitut = Character.toLowerCase(substitut);
                    }
                    novaParaula = novaParaula + substitut;
                    trobat = true;
                    break;
                }
            }

            if (!trobat) {
                novaParaula = novaParaula + c;
            }
        }
        return novaParaula;
    }

    public static String desxifraAlfa(String paraula) {
        String novaParaula = "";
        for (int i = 0; i < paraula.length(); i++) {
            char c = paraula.charAt(i);
            char maj = Character.toUpperCase(c);
            boolean trobat = false;

            for (int j = 0; j < alfabetPermutat.length; j++) {
                if (maj == alfabetPermutat[j]) {
                    char substitut = alfabetOriginal[j];
                    if (Character.isLowerCase(c)) {
                        substitut = Character.toLowerCase(substitut);
                    }
                    novaParaula = novaParaula + substitut;
                    trobat = true;
                    break;
                }
            }

            if (!trobat) {
                novaParaula = novaParaula + c;
            }
        }
        return novaParaula;
    }

    public static void main(String[] args) {
        System.out.println(alfabetOriginal);
        System.out.println(alfabetPermutat);

        String[] frasesProves = {"ABC", "XYZ", "Hola, Mr. calçot!", "Perdó, per tu què és?"};

        for (int i = 0; i < frasesProves.length; i++) {
            String xifrada = monoAlfa(frasesProves[i]);
            String desxifrada = desxifraAlfa(xifrada);

            System.out.println("Original: " + frasesProves[i]);
            System.out.println("Xifrada: " + xifrada);
            System.out.println("Desxifrada: " + desxifrada);
            System.out.println();
        }
    }
}
