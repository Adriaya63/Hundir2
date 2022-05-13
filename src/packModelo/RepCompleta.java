package packModelo;

import java.util.ArrayList;

public class RepCompleta implements Reparacion {

    @Override
    public Object[] repararBarco(Barco b , int pos, int j) {
        ArrayList<Integer> ld = new ArrayList<Integer>();
        ArrayList<Integer> disp = new ArrayList<Integer>();
        if (!b.estaHundido()&&b.estaTocado()){
            ArrayList<Integer> posRep = b.getlTocados();
            int i=0;
            while(posRep.size()!=0){
                int posR = posRep.get(i); 
                System.out.println("Reparando pos "+posR);
                b.repararPosicion(posR);
                ld = ListaJugadores.getMLista().eliminarDisparo(posR, j);
                disp.add(posR);
            }
           
        }
        return new Object[]{ld,disp};
    }
    
}
