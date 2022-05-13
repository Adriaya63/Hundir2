package packModelo;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class GestorTablero extends Observable{
    private static GestorTablero mGestorTablero = new GestorTablero();
    private String direccion;
    private int tipoBarco,indSelec,panel; /* escoger la casilla: IndSelec*/
    private boolean colocados;  /* boolean para determinar que estan todos colocados*/
    private ArrayList<Integer> lIndices; /* es una lista de numeros la cual sirve para colocar los barcos en cada casilla*/
    private ArrayList<Integer> dispPosibles;
    private Random numRadom;
    private int gameOver;
    private ArrayList<String> mCPU;

    private GestorTablero(){
        //Constructora del gestor
        numRadom = new Random();
        lIndices = new ArrayList<Integer>();
        dispPosibles = new ArrayList<Integer>();
        for(int i=12;i<121;i++){if(i%11!=0){dispPosibles.add(i);}}
        colocados = false;
        gameOver=0;
        iniciarJugador();
        iniciarCPU(); /* coje y vacia las cantidades y no te deja colocar ningun barco*/
        direccion = null;
        tipoBarco = 0;
    }

    public static GestorTablero getGestorTablero() {
        return mGestorTablero;
    }

    public void setTipoBarco(int tip) {
        tipoBarco = tip;
    }

    public void setDireccion(String dir) {
        direccion = dir;
    }

    public void setIndSelect(int ind, int p){
        indSelec = ind;
        panel = p;
        setChanged();
        notifyObservers(new Object[]{1,p,ind});
    }

    public ArrayList<Integer> visualizarBarco(){ 
        //Coloca ños barcos en el tablero
    	//Eneko, Adrian
        if(tipoBarco!=0 && direccion!=null){
            lIndices = new ArrayList<Integer>(); 
            int posiciones = 5-tipoBarco; /* 5 - tipoDeBarco es la cantidad de casillas*/
            int n1 = indSelec; /* guardas en indice del que has seleccionado para ir modificandolo*/
            int n2 = 0;
            for(int i=0;i<posiciones;i++){
                if(direccion=="Arriba"){
                    n2 = n1;
                    n1 = n2-11;  
                    lIndices.add(n2); /*en esta lista tienes las posiciones del barco*/
                }
                if(direccion=="Abajo"){
                    n2 = n1;
                    n1 = n2+11;
                    lIndices.add(n2);
                }  
                if(direccion=="Derecha"){
                    n2 = n1;
                    n1 = n2+1;
                    lIndices.add(n2);
                }  
                if(direccion=="Izquierda"){
                    n2 = n1;
                    n1 = n2-1;
                    lIndices.add(n2);
                }  
            }
            if (lIndices.stream().anyMatch(l->l<11||l>120||l%11==0)){lIndices = new ArrayList<Integer>();} /* filtro para saber si te has salido del tablero, si se ha salido se vacia la lista de indices*/
        }  
        setChanged();
        notifyObservers(new Object[]{2,lIndices});
        return lIndices; /*devuelve una lista con las posiciones*/
    }

    public void colocarBarco(){
        indSelec = 0;
        ArrayList<String> m = ListaJugadores.getMLista().colocarBarco(0, tipoBarco, direccion, lIndices);
        int[] cant = ListaJugadores.getMLista().getCantidadesJ(0);
        if(verificar(cant)){colocados=true;}
        setChanged();
        notifyObservers(new Object[]{3,m,cant});
    }

    private boolean verificar(int[] cant) {
        int n = 0;
        for (int i=0;i<4;i++){n += cant[i];}
        return n==0;
    }

    private void iniciarCPU(){
        boolean end = false;
        ArrayList<String> m = null;
        while(!end){ 
            indSelec = numRadom.nextInt(121);
            while(indSelec<12||indSelec%11==0){indSelec = numRadom.nextInt(121);} /* la cpu elige posicion random y mientras este en el rango se coloca el barco*/
            tipoBarco = numRadom.nextInt(4)+1;
            int d = numRadom.nextInt(4)+1; /* se elige la direccion de manera random*/
            if(d==1){direccion = "Arriba";}
            else if(d==2){direccion = "Abajo";}
            else if(d==3){direccion = "Derecha";}
            else if(d==4){direccion = "Izquierda";}
            visualizarBarco();
            m = ListaJugadores.getMLista().colocarBarco(1,tipoBarco, direccion, lIndices);
            int[] cant = ListaJugadores.getMLista().getCantidadesJ(1);
            end = verificar(cant);  
        }
        mCPU = m;
    }

    private void iniciarJugador(){
        boolean end = false;
        ArrayList<String> m = new ArrayList<String>();
        while(!end){ 
            indSelec = numRadom.nextInt(121);
            while(indSelec<12||indSelec%11==0){indSelec = numRadom.nextInt(121);} /* la cpu elige posicion random y mientras este en el rango se coloca el barco*/
            tipoBarco = numRadom.nextInt(4)+1;
            int d = numRadom.nextInt(4)+1; /* se elige la direccion de manera random*/
            if(d==1){direccion = "Arriba";}
            else if(d==2){direccion = "Abajo";}
            else if(d==3){direccion = "Derecha";}
            else if(d==4){direccion = "Izquierda";}
            visualizarBarco();
            m = ListaJugadores.getMLista().colocarBarco(0,tipoBarco, direccion, lIndices);
            end = verificar(ListaJugadores.getMLista().getCantidadesJ(0));  
        }
        colocados=true;
        int[] cant = ListaJugadores.getMLista().getCantidadesJ(0);
        setChanged();
        notifyObservers(new Object[]{3,m,cant});
    }

    public void cambiarArma(int i){
        ListaJugadores.getMLista().cambiarArma(i,0);
    }
    public void cambiarReparacion(int i){
        ListaJugadores.getMLista().cambiarReparacion(i,0);
    }

    public void turno(int metodo){
        if (colocados){
            boolean hecho = false;
            System.out.println("-----------------TURNO JUGADOR-----------------------");
            if(metodo==1&&panel==2){
                Object[] res = ListaJugadores.getMLista().disparar(0, indSelec);
                actualizarDisparos(res,1);
                hecho = true;   
            }
            else if(metodo==2&&panel==1){hecho = ListaJugadores.getMLista().ponerEscudo(indSelec, 0);}//Eneko, Adrian
            else if(metodo==3){
                int radar = numRadom.nextInt(121);
                while(radar<12||radar%11==0){
                    radar = numRadom.nextInt(121);}
                ArrayList<String> m = ListaJugadores.getMLista().usarRadar(radar, 0);
                hecho = true;
                setChanged();
                notifyObservers(new Object[]{10,1,radar,m});
            }
            else if(metodo==4&&panel==1){
                Object[] rep = ListaJugadores.getMLista().repararBarco(indSelec, 0);
                ArrayList<Integer> ld = (ArrayList<Integer>) rep[0];
                if(ld.size()!=0){
                    ArrayList<Integer> disp = (ArrayList<Integer>) rep[1];
                    System.out.println("Disparos de vuelta: "+disp.size());
                    disp.stream().forEach(l->dispPosibles.add(l));
                    hecho = true;
                    setChanged();
                    notifyObservers(new Object[]{6,ld,2});
                }  
            }//Miquel,Edu,Oscar
            if(hecho){
                boolean turnoCPU = false;
                int prob = numRadom.nextInt(100)-100;
                while(!turnoCPU){
                    System.out.println("-----------------TURNO CPU-----------------------");
                    if(prob<=50){
                        int probArm = numRadom.nextInt(100);
                        if(probArm<75){ListaJugadores.getMLista().cambiarArma(0, 1);}
                        else if(probArm>=75){ListaJugadores.getMLista().cambiarArma(1, 1);}
                        int dis = dispPosibles.get(numRadom.nextInt(dispPosibles.size()));
                        Object[] res = ListaJugadores.getMLista().disparar(1, dis);
                        ArrayList<Integer> ldis = (ArrayList<Integer>) res[1];
                        for(int i=0;i<ldis.size();i++){
                            int z = dispPosibles.indexOf(ldis.get(i));
                            if(z!=-1&& (int)res[0]!=3){dispPosibles.remove(z);}   
                        }
                        actualizarDisparos(res, 2);
                        turnoCPU = true;
                    }
                    else if(prob>50&&prob<=80){
                        int n = numRadom.nextInt(10);
                        turnoCPU = ListaJugadores.getMLista().ponerEscudo(n, 1);
                        if(!turnoCPU){prob = 0;}
                    }
                    else if(prob>80){
                        int radar = numRadom.nextInt(121);
                        while(radar<12||radar%11==0){
                        radar = numRadom.nextInt(121);}
                        ArrayList<String> m = ListaJugadores.getMLista().usarRadar(radar, 1);
                        if (m.size()!=0){
                            turnoCPU=true;
                            setChanged();
                            notifyObservers(new Object[]{10,2,radar,m});
                        }
                        
                    }
                }
            } 
            // if(Jugador.getJugador().haPerdido()){gameOver=1;}
            // if(CPU.getmCPU().haPerdido()){gameOver=2;}
            // indSelec = 0;
            // setChanged();
            // notifyObservers();
            // Jugador.getJugador().resetearRadar();
            // CPU.getmCPU().resetearRadar();*/
        } 
    }
    private void actualizarDisparos(Object[] res,int j){
        if(res!=null){
            ArrayList<Integer> lD = null;
            int n = 0;  
            if((int)res[0]==1){lD = (ArrayList<Integer>) res[1];n = 5;}
                    if((int)res[0]==2){lD = (ArrayList<Integer>) res[1];n = 6;}
                    if((int)res[0]==3){lD = (ArrayList<Integer>) res[1];n = 7;}
                    if((int)res[0]==4){lD = (ArrayList<Integer>) res[1];n = 8;}
                    setChanged();
                    notifyObservers(new Object[]{n,lD,j}); 
        }
                
    }
}
