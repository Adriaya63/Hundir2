package packModelo;

public class Escudo {
    private int vidas;

    public Escudo(){
        vidas = 2;
    }

    public boolean reducirEscudo(){
        boolean rdo = false;
        vidas-=1;
        if(vidas == 0){rdo = true;}
        return rdo;
    }
}
