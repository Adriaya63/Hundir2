package packModelo;

import java.util.ArrayList;

public class ListaJugadores{
    private static ListaJugadores mLista = null;
    private ArrayList<Jugador> lJugadores;

    private ListaJugadores(){
        lJugadores = new ArrayList<Jugador>();
        Jugador j1 = new Jugador("Jugador");
        lJugadores.add(j1);
        Bot bot1 = new Bot();
        lJugadores.add(bot1);
    }

    public static ListaJugadores getMLista(){
        if (mLista==null){
            mLista = new ListaJugadores();
        }
        return mLista;
    }

    public ArrayList<String> colocarBarco(int j,int tipoBarco, String direccion, ArrayList<Integer> lIndices){
        ArrayList<String> m = lJugadores.get(j).colocarBarco(tipoBarco, direccion, lIndices);
        return m;
    }

    public int[] getCantidadesJ(int j){
        return lJugadores.get(j).getCantidadesJ();
    } 

    public void cambiarArma(int i,int j) {
        if(i==0) {lJugadores.get(j).setcArma(new Bomba());}
        else if(i==1) {lJugadores.get(j).setcArma(new Misil());}
    }
    public void cambiarReparacion(int i,int j) {
        if(i==0) {lJugadores.get(j).setcReparacion(new RepParcial());}
        else if(i==1) {lJugadores.get(j).setcReparacion(new RepCompleta());}
    }
    public Object[] disparar(int j,int pos){
        return lJugadores.get(j).disparar(pos);
    }
    public Barco barcoPos(int pos, int j){
        return lJugadores.get(j).barcoEnPos(pos);
    }
    public void eliminarHundido(Barco b,int j){
        lJugadores.get(j).eliminarBarcoHundido(b);
    }

    public boolean ponerEscudo(int pos,int j){    
        return lJugadores.get(j).ponerEscudo(pos);
    }

    public ArrayList<String> usarRadar(int pos, int j){
        return lJugadores.get(j).usarRadar(pos);
    }

    public ArrayList<String> mostrarRadar(int pos, int j){
        return lJugadores.get(j).mostrarRadar(pos);
    }

    public Object[] repararBarco(int pos, int j){
        return lJugadores.get(j).repararBarco(pos);
    }

    public ArrayList<Integer> eliminarDisparo(int pos, int j) {
        return lJugadores.get(j).eliminarDisparo(pos);
    }

    public boolean comprarObj(Object o, int precio, int j){
        return lJugadores.get(j).comprarObj(o, precio);
    }

    public int[] getCantidadObj(int j){
        return lJugadores.get(j).getCantidadObj();
    }

    public int buscarGanador(){
        int rdo = 0;
        for(int i=0;i<lJugadores.size();i++){
            if(lJugadores.get(i).haPerdido()){
                rdo = i+1;
            }
        }
        return rdo;
    }
    

}
