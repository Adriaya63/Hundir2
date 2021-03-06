package packModelo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

import javax.print.DocFlavor.STRING;

public class Jugador {
    private String nombre;
    private ArrayList<Barco> lBarcosJ;
    private ArrayList<String> matrizJ;
    private ArrayList<Integer> lDisparosB, lDisparosA,lEscudos;
    private int cantidadesJ[];
    private Arma cArma;
    private Reparacion cReaparacion;
    private ArrayList<Integer> lHundidos;
    private Queue<Escudo> nEscudos;
    private Queue<Radar> nRadar;
    private int nMisiles;
    private Monedero monedero;
    private Radar radar;

    public Jugador(String name){
        nombre = name;
        nMisiles = 0;
        monedero = new Monedero(50);
        radar = new Radar();
        matrizJ = new ArrayList<String>();
        lDisparosB = new ArrayList<Integer>();
        lDisparosA = new ArrayList<Integer>();
        lEscudos = new ArrayList<Integer>();
        lBarcosJ= new ArrayList<Barco>();
        lHundidos = new ArrayList<Integer>();
        nEscudos = new LinkedList<Escudo>();
        nRadar = new LinkedList<Radar>();
        cantidadesJ = new int[4];
        cArma = new Bomba();
        cReaparacion = new RepParcial();        
        for (int a=0;a<4;a++){cantidadesJ[a] = a+1;}
        String c;
        for (int i=0;i<11;i++){
            matrizJ.add("#"); /* colocar en las matrices los limites en forma de # */
            for (int j=0;j<10;j++){
                if(i==0){c = "#";}
                else{c = " ";}
                matrizJ.add(c);
            }
        } 
    }

    public ArrayList<Barco> getlBarcosJ() {
        return lBarcosJ;
    }

    public Radar getRadar() {
        return radar;
    }

    public Queue<Escudo> getnEscudos() {
        return nEscudos;
    }

    public int[] getCantidadesJ() {
        return cantidadesJ;
    }

    private void ponerAgua(int i){
        if(i>11&&i<=120&&i%11!=0){
            matrizJ.set(i, "a");            
        }
    }

    public ArrayList<String> colocarBarco(int tipoBarco, String direccion, ArrayList<Integer> lIndices){
        if(tipoBarco!=0 && direccion!=null && lIndices.size()!=0){ /* saber si has seleccionado y que el barco seleccionado se pueda colocar*/
            if (cantidadesJ[tipoBarco-1]!=0){ /* cantidad de barcos que tienes, p.ej solo tienes una gabarra*/
                if(!lIndices.stream().anyMatch(l->matrizJ.get(l)=="a"||matrizJ.get(l)=="b")){  /* de la lista de indices mira en la matriz si en las posiciones no hay ni barco ni agua*/
                    cantidadesJ[tipoBarco-1] = cantidadesJ[tipoBarco-1]-1; /* se actualiza la cant de barcos*/
                    for(int i=0;i<5-tipoBarco;i++){ 
                        if(direccion=="Arriba"){  /* primero se detecta la direccion*/
                            if(i==0){
                                matrizJ.set(lIndices.get(i), "b"); /* primero se coloca el barco, se pone b en la posicion indicada por lIndices, en la posicion en la que estas se pone una b*/
                                ponerAgua(lIndices.get(i)-1);
                                ponerAgua(lIndices.get(i)+1);
                                ponerAgua(lIndices.get(i)+11);
                                ponerAgua(lIndices.get(i)-11);
                                ponerAgua(lIndices.get(i)-12);
                                ponerAgua(lIndices.get(i)+12);
                                ponerAgua(lIndices.get(i)-10);
                                ponerAgua(lIndices.get(i)+10);

                            }else if(i+1==5-tipoBarco){  /* se pone b al final del indice*/
                                matrizJ.set(lIndices.get(i), "b");
                                ponerAgua(lIndices.get(i)-1 );
                                ponerAgua(lIndices.get(i)+1 );
                                ponerAgua(lIndices.get(i)-11);
                                ponerAgua(lIndices.get(i)-12);
                                ponerAgua(lIndices.get(i)-10);
                            }else{   /* se pone b en la casilla de en medio*/
                                matrizJ.set(lIndices.get(i),"b");
                                ponerAgua(lIndices.get(i)-1);
                                ponerAgua(lIndices.get(i)+1);
                            }
                        }
                        if(direccion=="Abajo"){
                            if(i==0){
                                matrizJ.set(lIndices.get(i), "b");
                                ponerAgua(lIndices.get(i)-1);
                                ponerAgua(lIndices.get(i)+1);
                                ponerAgua(lIndices.get(i)-11);
                                ponerAgua(lIndices.get(i)+11);
                                ponerAgua(lIndices.get(i)-12);
                                ponerAgua(lIndices.get(i)+12);
                                ponerAgua(lIndices.get(i)-10);
                                ponerAgua(lIndices.get(i)+10);
                            }else if(i+1==5-tipoBarco){
                                matrizJ.set(lIndices.get(i), "b");
                                ponerAgua(lIndices.get(i)-1);
                                ponerAgua(lIndices.get(i)+1);
                                ponerAgua(lIndices.get(i)+11);
                                ponerAgua(lIndices.get(i)+12);
                                ponerAgua(lIndices.get(i)+10);
                            }else{
                                matrizJ.set(lIndices.get(i), "b");
                                ponerAgua(lIndices.get(i)-1);
                                ponerAgua(lIndices.get(i)+1);
                            }
                        }
                        if(direccion=="Derecha"){
                            if(i==0){
                                matrizJ.set(lIndices.get(i), "b");
                                ponerAgua(lIndices.get(i)-11);
                                ponerAgua(lIndices.get(i)+11);
                                ponerAgua(lIndices.get(i)+1);
                                ponerAgua(lIndices.get(i)-1);
                                ponerAgua(lIndices.get(i)-12);
                                ponerAgua(lIndices.get(i)+12);
                                ponerAgua(lIndices.get(i)-10);
                                ponerAgua(lIndices.get(i)+10);
                            }else if(i+1==5-tipoBarco){
                                matrizJ.set(lIndices.get(i), "b");
                                ponerAgua(lIndices.get(i)-11);
                                ponerAgua(lIndices.get(i)+11);
                                ponerAgua(lIndices.get(i)+1);
                                ponerAgua(lIndices.get(i)+12);
                                ponerAgua(lIndices.get(i)-10);
                            }else{
                                matrizJ.set(lIndices.get(i), "b");
                                ponerAgua(lIndices.get(i)-11);
                                ponerAgua(lIndices.get(i)+11);
                            }
                        }
                        if(direccion=="Izquierda"){
                            if(i==0){
                                matrizJ.set(lIndices.get(i), "b");
                                ponerAgua(lIndices.get(i)-11);
                                ponerAgua(lIndices.get(i)+11);
                                ponerAgua(lIndices.get(i)+1);
                                ponerAgua(lIndices.get(i)-1);
                                ponerAgua(lIndices.get(i)-12);
                                ponerAgua(lIndices.get(i)+12);
                                ponerAgua(lIndices.get(i)-10);
                                ponerAgua(lIndices.get(i)+10);
                            }else if(i+1==5-tipoBarco){
                                matrizJ.set(lIndices.get(i), "b");
                                ponerAgua(lIndices.get(i)-11);
                                ponerAgua(lIndices.get(i)+11);
                                ponerAgua(lIndices.get(i)-1);
                                ponerAgua(lIndices.get(i)-12);
                                ponerAgua(lIndices.get(i)+10);
                            }else{
                                matrizJ.set(lIndices.get(i), "b");
                                ponerAgua(lIndices.get(i)-11);
                                ponerAgua(lIndices.get(i)+11);
                            }
                        }
                    }
                    if (verificar()) {rellenarAgua();}
                    Barco b=  BarcoFactory.getBarcoFactory().createBarco(tipoBarco,lIndices);
                    lBarcosJ.add(b);
                }
            }
        }
        return matrizJ;
    }
    public boolean verificar() {
    	if(cantidadesJ[0]==0&&cantidadesJ[1]==0&&cantidadesJ[2]==0&&cantidadesJ[3]==0) {
    		return true;
    	}
    	return false;
    }
    private void rellenarAgua() {
    	
        for(int i=0; i<matrizJ.size();i++) {
            
            if(matrizJ.get(i).equals(" ")) {
                matrizJ.set(i, "a");
            }
        }		
    }
    public ArrayList<String> getMatrizJ() {
        return matrizJ;
    }

    public void setcArma(Arma a) {
        cArma = a;
    }
    public void setcReparacion(Reparacion cReaparacion) {
        this.cReaparacion = cReaparacion;
    }

    public Object[] disparar(int pos){
        int j = 0;
        if(nombre.equals("Jugador")){j = 1;}
        else if(nombre.equals("Bot")){j = 0;}
        if(!(lDisparosB.stream().anyMatch(l -> l==pos)||lDisparosA.stream().anyMatch(l -> l==pos)||lHundidos.stream().anyMatch(l -> l==pos))){  
            Object[] o = cArma.disparar(pos,j,nMisiles);
            int n = (int) o[0];
            ArrayList<Integer> lpos = (ArrayList<Integer>) o[1];
            if(cArma instanceof Misil&&n!=0){nMisiles-=1;}
            if(n==1){
                lpos.stream().forEach(l->lDisparosA.add(l));
                return new Object[]{1,lDisparosA,pos};
            }else if(n==2){
                lpos.stream().forEach(l->lDisparosB.add(l));
                monedero.modificarDinreo(10);
                return new Object[]{2,lDisparosB,pos};
            }else if(n==3){
                lpos.stream().forEach(l->lEscudos.add(l));
                return new Object[]{3,lEscudos,pos};
            }else if(n==4){
                Barco b = ListaJugadores.getMLista().barcoPos(pos,j);
                b.getLPosiciones().stream().forEach(l->lHundidos.add(l));
                b.getLPosiciones().stream().forEach(l->lDisparosB.remove(l));
                b.getLPosiciones().stream().forEach(l->monedero.modificarDinreo(5));
                ListaJugadores.getMLista().eliminarHundido(b, j);
                return new Object[]{4,lHundidos,pos};
            }
        }
        return null;
    }

    public Object[] repararBarco(int pos){
        int j = 0; 
        Object[] ld = new Object[2];
        if(nombre.equals("Jugador")){j = 1;}
        else if(nombre.equals("Bot")){j = 0;}
        Barco b = barcoEnPos(pos);
        if (b!=null){
            if(cReaparacion instanceof RepCompleta){
                System.out.println("Reparando C");
                int costo = 10*b.getlTocados().size();
                if(monedero.puedeReducir(costo)){
                    monedero.modificarDinreo(-costo);
                    ld = cReaparacion.repararBarco(b,pos,j);
                }
            }
            else if(cReaparacion instanceof RepParcial){
                System.out.println("Reparando P");
                if(monedero.puedeReducir(10)){
                    monedero.modificarDinreo(-10);
                    ld = cReaparacion.repararBarco(b,pos,j);
                }
            }
        }
        System.out.println("Jugador:");
        System.out.println(ld[0]);
        System.out.println(ld[1]);
        return ld;
    }

    public ArrayList<Integer> eliminarDisparo(int pos){
        lDisparosB.remove(lDisparosB.indexOf(pos));
        return lDisparosB;
    }

    public boolean sePuedeDisparar(int pos){
        return !(lDisparosB.stream().anyMatch(l -> l==pos)||lDisparosA.stream().anyMatch(l -> l==pos)||lHundidos.stream().anyMatch(l -> l==pos));
    }

    public Barco barcoEnPos(int pos){
        Barco b = null;
        if(matrizJ.get(pos)=="a"){b = null;}
        else if(matrizJ.get(pos)=="b" && lBarcosJ.stream().anyMatch(l -> l.tienePosicion(pos)))
        { b=lBarcosJ.stream().filter(l -> l.tienePosicion(pos)==true).collect(Collectors.toList()).get(0);}
        return b;
    }

    public void eliminarBarcoHundido(Barco b){
        lBarcosJ.remove(b);
    }

    public boolean ponerEscudo(int pos){
        boolean rdo = false;
        Barco b = barcoEnPos(pos);
        if (b!=null ){
            if(!b.tieneEscudo()&&!b.estaTocado()&&!nEscudos.isEmpty()){
                Escudo e = nEscudos.remove();
                b.ponerEscudo(e);
                rdo = true;
            }
        }
        return rdo;
    }

    public ArrayList<String> usarRadar(int pos){
        ArrayList<String> m = null;
        if(!nRadar.isEmpty()){
            Radar rad = nRadar.remove();
            rad.activarRadar(pos, 1);
            System.out.println("Usando radar en pos "+pos);
            m = rad.getMatriz();
        }
        return m;
    }

    public ArrayList<String> mostrarRadar(int pos){
        ArrayList<String> rd = new ArrayList<String>();
        anadirRadar(rd, pos-12);
        anadirRadar(rd, pos-11);
        anadirRadar(rd, pos-10);
        anadirRadar(rd, pos-1);
        anadirRadar(rd, pos);
        anadirRadar(rd, pos+1);
        anadirRadar(rd, pos+10);
        anadirRadar(rd, pos+11);
        anadirRadar(rd, pos+12);
        return rd;        
    }
    private ArrayList<String> anadirRadar(ArrayList<String> m ,int pos){
        if(pos>11&&pos<=120&&pos%11!=0){
            m.add(matrizJ.get(pos));           
        }
        else{m.add("#");}
        return m;
    }

    private void anadirObjeto(Object obj){
        if (obj instanceof Escudo){
            Escudo e = (Escudo) obj;
            nEscudos.add(e);
            System.out.println("A??adido escudo");
        }else if(obj instanceof Radar){
            Radar r = (Radar) obj;
            nRadar.add(r);
            System.out.println("A??adido radar");
        }else if(obj instanceof Misil){
            nMisiles += 1;
            System.out.println("A??adido misil");
        }
    }

    public boolean comprarObj(Object o, int precio){
        if(monedero.puedeReducir(precio)){
            monedero.modificarDinreo(-precio);
            anadirObjeto(o);
            
            return true;
        }
        return false;
    }

    public int[] getCantidadObj(){
        int e = nEscudos.size();
        int r = nRadar.size();
        int m = nMisiles;
        int d = monedero.getDinero();
        return new int[]{e,r,m,d};
    }

    public boolean haPerdido(){
        return lBarcosJ.size()==0;
    }
    
}
