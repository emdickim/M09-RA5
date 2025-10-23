// Aixó es un programet en java que xifra i descifra missatges utilitzant el xifratge polialfabètic.
import java.util.*;

public class Polialfabetic {
    
    private static final char[] alfabetOriginal = "ABCDEFGHIJKLMNOPQRSTUVWXYZÀÈÉÌÍÏÒÓÙÚÜÑÇ".toCharArray();
    private static final char[] alfabetPermutat = alfabetOriginal.clone();
    private static long clauSecreta = 606373123;
    private static Random rnd = null;
    public static char[] permutaAlfabetANTIC(char[] abc) {
        List<Character> remixAbcedari = new ArrayList<>();

        for (int i = 0; i < abc.length; i++) {
            remixAbcedari.add(abc[i]);
        }

        Collections.shuffle(remixAbcedari, rnd);

        for (int i = 0; i < abc.length; i++) {
            abc[i] = remixAbcedari.get(i);
        }

        return abc;
    }
    public static void initRandom(long numSecret) {
        rnd = new Random(numSecret);
    }
    
    public static void permutaAlfabet() {
        List<Character> remixAbcdari = new ArrayList<>();

        for (int i = 0; i < alfabetOriginal.length; i++) {
            remixAbcdari.add(alfabetOriginal[i]);
        }
        
        Collections.shuffle(remixAbcdari, rnd);

        for (int i = 0; i < alfabetOriginal.length; i++) {
            alfabetPermutat[i] = remixAbcdari.get(i);
        }

    }

    public static String xifraPoliAlfa(String msg) {
        String textXifrat = "";
        for (int i = 0; i < msg.length(); i++) {
            permutaAlfabet();
            for (int j = 0; j < alfabetOriginal.length; j++) {
                if (Character.toUpperCase(msg.charAt(i)) == alfabetOriginal[j]) {
                    if (Character.isUpperCase(msg.charAt(i))) {
                        textXifrat += alfabetPermutat[j];
                    } else if (Character.isLowerCase(msg.charAt(i))) {
                        textXifrat += Character.toLowerCase(alfabetPermutat[j]);
                    }                     
                }
                if (!Character.isLetter(msg.charAt(i))) {
                        textXifrat += msg.charAt(i);
                        break;
                    }
            }
        }
                    

        return textXifrat;
    }

    public static String desxifraPoliAlfa(String msgXifrat) {
        String textXifrat = "";
        
        for (int i = 0; i < msgXifrat.length(); i++) {
            permutaAlfabet();
            for (int j = 0; j < alfabetPermutat.length; j++) {
                if (Character.toUpperCase(msgXifrat.charAt(i)) == alfabetPermutat[j]) {
                    if (Character.isUpperCase(msgXifrat.charAt(i))) {
                        textXifrat += alfabetOriginal[j];
                        
                    } else if (Character.isLowerCase(msgXifrat.charAt(i))) {
                        textXifrat += Character.toLowerCase(alfabetOriginal[j]);
                    }                     
                }
                if (!Character.isLetter(msgXifrat.charAt(i))) {
                        textXifrat += msgXifrat.charAt(i);
                        break;
                    }
            }
        }
                    

        return textXifrat;
    }

        
    
    public static void main(String[] args) {
        String msgs[] = {"Test 01 àrbitre, coixí, Perímetre",
                    "Test 02 Taüll, DÍA, año",
                    "Test 03 Peça, Òrrius, Bòvila"};
        
        String msgsXifrats[]=new String[msgs.length];

        System.out.println("Xifratge:\n-------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }
        System.out.println("Desxifratge:\n-------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msg = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }
}
