package packModelo;

import java.util.ArrayList;

public abstract class Barco {

	private ArrayList<Integer> lPosiciones;
	private ArrayList<Integer> lTocados;
	private boolean hundido;
	private String nombre;
	private Escudo escudo;
	
	public Barco(String nom,ArrayList<Integer> lPosiciones) {
		this.nombre = nom;
		this.lPosiciones = lPosiciones;
		this.lTocados = new ArrayList<Integer>();
	}

	public ArrayList<Integer> getLPosiciones() {
		return lPosiciones;
	}

	public ArrayList<Integer> getlTocados() {
		return lTocados;
	}

	public boolean tienePosicion(int pos) {	
		boolean d =lPosiciones.stream().anyMatch(l->l==pos);
		return d;
	}

	public void printPosiciones() {
		System.out.println(nombre);
		lPosiciones.stream().forEach(l->System.out.println(l));
	}

	public boolean estaTocado(){
		return lTocados.size()!=0;
	}

	public void hundirBarco(){
		lPosiciones.stream().forEach(l->lTocados.add(l));
	}
		
	public boolean anadirYComprobar(int pos) {
		boolean b = false;
		lTocados.add(pos);
		System.out.println(nombre+": Posicion "+pos+" anadida.");
		b = estaHundido();
		return b;
	}
	
	public boolean estaHundido() {	
		int cont=0;
		hundido=false;		
		for(int i=0; i<lPosiciones.size();i++) {
			if(lTocados.indexOf(lPosiciones.get(i))!=-1) {
				cont++;
			}
			if(lPosiciones.size()==cont) {	
				hundido=true;
			}
		}
		return hundido;
	}

	public void ponerEscudo() {
		escudo = new Escudo();
		System.out.println("Escudo creado "+lPosiciones);

	}

	public boolean tieneEscudo(){
		return escudo != null;
	}

	public boolean tocarEscudo(){
		if(escudo.reducirEscudo()){escudo=null;return true;}
		return false;
	}

	public boolean posicionTocada(int pos){
		return lTocados.stream().anyMatch(l->l==pos);
	}

	public void repararPosicion(int pos){
		lTocados.remove(lTocados.indexOf(pos));
	}
	
	
	/*public boolean loHasHundido(){
		
		hundido=false;
		int cont=0;
		
		for(int i=0; i<lPosiciones.size();i++) {
			
			if(loHasTocado(i)) {
				cont++;
				
			}

			if(lPosiciones.size()==cont) {
				hundido=true;
			}
		}
		
		return hundido;
		
	}
	*/
	
}