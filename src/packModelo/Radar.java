package packModelo;

import java.util.ArrayList;

public class Radar {
    private ArrayList<String> matriz;

    public Radar(){
        matriz = new ArrayList<String>();
    }

    public void activarRadar(int pos,int j){
        matriz = ListaJugadores.getMLista().mostrarRadar(pos, j);
    }
    public ArrayList<String> getMatriz() {
        return matriz;
    }
}
