/* */
public class Rot13 {
    char[] AbcMinisculas ="abcdefghijklmnopqrstuvwxyzàèéìíïòóùúüñç".toCharArray();

    char[] AbcMayusculas ="ABCDEFGHIJKLMNOPQRSTUVWXYZÀÈÉÌÍÏÒÓÙÚÜÑÇ".toCharArray();
    public static String xifraRot13 (String text) {
        String xd = "";
        for (int i = 0; i < AbcMinisculas.length; i++) {
            
            
            //if (Character.isUpperCase(text.charAt(i))) {

            //}
        }
        return xd;
    }
    public static void main(String[] args) {
        System.out.println("Escriu el text que s'ha de xifrar");
        String text = "Mr. calçot";
        System.out.println(xifraRot13(text));
    }


    //String[] text = {ABC,XYZ,Hola, Mr. calçot,Perdó, per tu què és?}; 
}