package iticbcn.xifratge;

public interface Xifrador {
    public TextXifrat xifra(String msg, String clau) throws ClauNoSoportada {

    }
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSoportada {

    }
    
}
