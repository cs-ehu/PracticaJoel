package pmyoo;
/**
 * Crea un enumerado con las direcciones
 * @author Joel Villalobos
 * @version 2017.06.04
 */
public enum Direccion {
	 ESTE(0), SUR(90), OESTE(180), NORTE(270);
    private int direccion;
    /**
     * Asigna una direccion a direccion
     * @param pDireccion La direccion que que se asigna
     */
    Direccion (int pDireccion){
        direccion = pDireccion;
    }
    /**
     * 
     * @return El valor de la direccion, los grados
     */
     public int getValor(){
        return direccion;
    }
}
