package packModelo;

public class Monedero {
    private int dinero;

    public Monedero(int pDinero){
        dinero = pDinero;
    }

    public int getDinero() {
        return dinero;
    }

    public boolean puedeReducir(int cant){
        return cant<=dinero;
    }

    public void modificarDinreo(int cant){
        dinero = dinero+cant;
    }
}
