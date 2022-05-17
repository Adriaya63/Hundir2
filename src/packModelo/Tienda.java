package packModelo;

import java.util.Observable;

public class Tienda extends Observable{
	private static Tienda mTienda;
	private int precioEs = 25;
	private int precioMi = 30;
	private int precioRad = 15;
	
	private Tienda() {}
	public static Tienda getTienda() {
		if(mTienda == null) {
			mTienda = new Tienda();
		}
		return mTienda;
	}

	public boolean comprarObjeto(int tipo,int j) {
		Object ob = null;
		int precio = 0;
		if(tipo==1){
			ob = new Misil();
			precio = precioMi;
		}else if(tipo==2){
			ob = new Escudo();
			precio = precioEs;
		}else if(tipo==3){
			ob = new Radar();
			precio = precioRad;
		}
		boolean b = ListaJugadores.getMLista().comprarObj(ob, precio, j);
		System.out.println("comprando "+tipo);
		if (j == 0){
			int[] obj = getCantObj(j);
			setChanged();
			notifyObservers(obj);
		}
		return b;
		
	}

	public int[] getCantObj(int j){
		return ListaJugadores.getMLista().getCantidadObj(j);
	}
}
