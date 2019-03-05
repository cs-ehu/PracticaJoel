package pmyoo;

import java.awt.Color;
/**
 * Crea un stock donde añade las estanterias por colores
 * @author Joel Villalobos
 *
 */
public class Stock {
	private Color[] colores;
	private Estanteria[] estanterias;
	public Stock( Color[] pColores ){
		colores = new Color[pColores.length];
		for( int i = 0 ; i < colores.length ; i++  )
		{
			colores[i] = pColores[i];
		}			
		estanterias = new Estanteria[ pColores.length ];
		for( int i = 0 ; i < pColores.length ; i++ )
		{
			estanterias[i] = new Estanteria( colores[i] );
		}
	}
	/**
	 * 
	 * @return Un array de estanterias
	 */
	public Estanteria[] getEstanterias(){
		return estanterias;
	}
	/**
	 * 
	 * @return Un array de colores
	 */
	public Color[] getColores(){
		return colores;
	}
	/**
	 * 
	 * @return La longitud del array de estanterias, es decir, el número de estanterias que hay
	 */
	public int getNumEstanterias(){
		return estanterias.length;
	}
	/**
	 * 
	 * @param color El color de la fila
	 * @return La posición del array de colores si se ha encontrado y si no devuelve -1
	 */
	public int buscarPosicion(Color color){
		int posicion = 0;
		int i = 0;
		boolean encontrado = false;
		while (i<colores.length && encontrado == false){
			if (colores[i].equals(color)){
				encontrado = true;
				posicion = i;
			}
			else{
				posicion = -1;
				i++;
			}
		}
		return posicion;
	}
	/**
	 * Añade una caja en la posición buscada del array de estanterias
	 * @param color El color de la fila de la caja que se quiere añadir
	 * @param caja La caja que se quiere añadir
	 */
	public void anadirCaja(Color color, Caja caja) {	
		estanterias[buscarPosicion(color)].anadirCaja(caja);
	}
	/**
	 * 
	 * @param color El color de la fila de la caja que se quiere recuperar
	 * @return La caja recuperada de la posición buscada del array de estanterias
	 */
	public Caja recuperarPrimeraCaja(Color color){
		return estanterias[buscarPosicion(color)].recuperarPrimeraCaja();
	}
	/**
	 * Sirve la caja de la posición buscada del array de estanterias
	 * @param color El color de la fila de la caja que se quiere servir
	 * @param caja La caja que va a ser servida
	 */
	public void cajaServida(Color color, Caja caja){
		estanterias[buscarPosicion(color)].cajaServida(caja);
	}
}
