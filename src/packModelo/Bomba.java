package packModelo;

import java.util.ArrayList;

public class Bomba implements Arma {

    @Override
    public Object[] disparar(int pos, int j, int nMisiles) {
        int n = 0;
        ArrayList<Integer> ldis = new ArrayList<Integer>();
        Barco b = ListaJugadores.getMLista().barcoPos(pos, j);
        if(b==null){n=1;ldis.add(pos);}
        else{
            if(b.tieneEscudo()){
                n=3;
                b.tocarEscudo();
                ldis.add(pos);
            }
            else{
                n=2;
                ldis.add(pos);
                boolean hund = b.anadirYComprobar(pos);
                if(hund){n=4;}
            }
        }
        return new Object[]{n,ldis};
    }
    
}
