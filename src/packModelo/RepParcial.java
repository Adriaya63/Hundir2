package packModelo;

import java.util.ArrayList;

public class RepParcial implements Reparacion {

    @Override
    public Object[] repararBarco(Barco b , int pos, int j) {
        ArrayList<Integer> ld = new ArrayList<Integer>();
        ArrayList<Integer> disp = new ArrayList<Integer>();
        if (!b.estaHundido()&&b.estaTocado()){
            System.out.println("Reparando pos "+pos);
            b.repararPosicion(pos);
            disp.add(pos);
            ld = ListaJugadores.getMLista().eliminarDisparo(pos, j);
        }
        System.out.println("Reparacion:");
        System.out.println(ld);
        System.out.println(disp);
        return new Object[]{ld,disp};
    }
    
}
