package pmyoo;

import java.awt.Color;
import java.util.ArrayList;
/**
 * Crea estanterias y le añade cajas
 * @author Joel Villalobos
 * @version 2017.06.04
 */

public class Estanteria {
	private Color color;
	private ArrayList<Caja> cajas;
	public Estanteria(Color pColor){
		color = pColor;
		cajas = new ArrayList<Caja>();
	}
	/**
	 * 
	 * @return El color
	 */
	public Color getColor(){
		return color;
	}
	/**
	 * 
	 * @return Una lista con las cajas
	 */
	public ArrayList<Caja> getCajas(){
		return cajas;
	}
	/**
	 * Añade una caja a la lista de las cajas
	 * @param caja La caja que añade a la lista
	 */
	public void anadirCaja(Caja caja){
		cajas.add(caja);
	}
	/**
	 * 
	 * @return La caja que esta en la primera posicion de la lista
	 */
	public Caja recuperarPrimeraCaja(){
		return cajas.get(0);
	}
	/**
	 * Borra la caja que ya ha sido servida al almacen
	 * @param caja La caja que ha sido servida
	 */
	public void cajaServida(Caja caja){
		cajas.remove(caja);
	}
}
