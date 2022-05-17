package packModelo;

import java.util.ArrayList;

public class Misil implements Arma {

    @Override
    public Object[] disparar(int pos, int j, int nMisiles) {
        // TODO Auto-generated method stub
        int n = 0;
        ArrayList<Integer> ldis = new ArrayList<Integer>();
        if(nMisiles>0){
            Barco b = ListaJugadores.getMLista().barcoPos(pos, j);
            if(b==null){n=1;ldis.add(pos);}
            else{
                if(b.tieneEscudo()){
                    n=3;
                    boolean rdo = b.tocarEscudo();
                    while(!rdo){rdo = b.tocarEscudo();}
                    b.getLPosiciones().stream().forEach(l->ldis.add(l));
                }
                else{
                    n=4;
                    b.hundirBarco();
                    b.getLPosiciones().stream().forEach(l->ldis.add(l));
                }
            }
        }
        return new Object[]{n,ldis};
    }
    
}
