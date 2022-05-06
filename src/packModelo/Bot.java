package packModelo;

import java.util.ArrayList;

public class Bot extends Jugador{

    public Bot() {
        super("Bot");
        printMatriz();
    }

    public void printMatriz() {
        ArrayList<String> matrizj = super.getMatrizJ();
        for (int z=0;z<11;z++){
            String row = "";
            for (int z2=0;z2<11;z2++){
                row = row+matrizj.get(z*11+z2);
            }
            System.out.println(row);
        }

        
    }
    @Override
    public Object[] disparar(int pos) {
        // TODO Auto-generated method stub
        return super.disparar(pos);
    }

}
