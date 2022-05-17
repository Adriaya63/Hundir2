package packModelo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Bot extends Jugador{

    private Queue<Integer> barcosObj;

    public Bot() {
        super("Bot");
        printMatriz();
        barcosObj = new LinkedList<Integer>();
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

    private void anadirObjetivo(int posRad, ArrayList<String> radar){
        if(radar.stream().anyMatch(l->l=="b")){
            for(int i=0;i<radar.size();i++){
                if(i==0&&radar.get(i)=="b"){barcosObj.add(posRad-12);}
                else if(i==1&&radar.get(i)=="b"){barcosObj.add(posRad-11);}
                else if(i==2&&radar.get(i)=="b"){barcosObj.add(posRad-10);}
                else if(i==3&&radar.get(i)=="b"){barcosObj.add(posRad-1);}
                else if(i==4&&radar.get(i)=="b"){barcosObj.add(posRad);}
                else if(i==5&&radar.get(i)=="b"){barcosObj.add(posRad+1);}
                else if(i==6&&radar.get(i)=="b"){barcosObj.add(posRad+10);}
                else if(i==7&&radar.get(i)=="b"){barcosObj.add(posRad+11);}
                else if(i==8&&radar.get(i)=="b"){barcosObj.add(posRad+12);}
            }
        }
    }
    @Override
    public ArrayList<String> usarRadar(int pos){
        System.out.println("Usando radar en pos "+pos);
        Radar radar = super.getRadar();
        radar.activarRadar(pos, 0);
        ArrayList<String> m = radar.getMatriz();
        anadirObjetivo(pos,m);
        return m;
    }
    @Override
    public Object[] disparar(int pos) {
        // TODO Auto-generated method stub
        boolean rdo = false;
        int disp = 0;
        while(!rdo){
            if(!barcosObj.isEmpty()){
                int b = barcosObj.remove();
                if(sePuedeDisparar(b)){
                    rdo = true;
                    disp = b;
                }
            }
            else {
                disp = pos;
                rdo = true;
            }
        }
        return super.disparar(disp);
    }

    @Override
    public boolean ponerEscudo(int pos) {
        if (pos>=super.getlBarcosJ().size()){pos = super.getlBarcosJ().size()-1; }
        Barco b = super.getlBarcosJ().get(pos);
        boolean rdo = false;
        Queue<Escudo> escudos = super.getnEscudos(); 
        if (b!=null){
            if(!b.tieneEscudo()&&!b.estaTocado()&& !escudos.isEmpty()){
                Escudo e = escudos.remove();
                b.ponerEscudo(e);
                rdo = true;
            }
        }
        return rdo;

    }

    @Override
    public Object[] repararBarco(int pos) {
        boolean enc = false;
        int rdo = 0;
        int i = 0;
        while(!enc && i<super.getlBarcosJ().size()){
            Barco b = super.getlBarcosJ().get(i);
            if (b.getlTocados().size()!=0){
                rdo = b.getlTocados().get(0);
                enc = true;
            }
        }
        if (!enc){return null;}
        return super.repararBarco(rdo);
    }

}
