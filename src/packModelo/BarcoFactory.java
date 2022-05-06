package packModelo;

import java.util.ArrayList;

public class BarcoFactory {
    private static BarcoFactory mBarcoFactory;

    private BarcoFactory(){}
    
    public static BarcoFactory getBarcoFactory() {
        if(mBarcoFactory==null){
            mBarcoFactory = new BarcoFactory();
        }
        return mBarcoFactory;
        
    }
    
    public Barco createBarco(int tipo, ArrayList<Integer> pos) {
    	Barco miBarco = null;
    	if(tipo==1) {miBarco = new Gabarra("Gabarra",pos);}
    	else if(tipo==2) { miBarco = new Velero("Velero",pos);}
    	else if(tipo==3) { miBarco = new Trainera("Trainera",pos);}
    	else if(tipo==4) { miBarco = new Txintxorro("Txintxorro",pos);}
    	return miBarco;
    }
    
}
