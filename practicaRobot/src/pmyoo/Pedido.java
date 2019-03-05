package pmyoo;

import java.awt.Color;
/**
 * Crea un pedido
 * @author Joel Villalobos
 * @version 2017.06.04
 */
public class Pedido {
	public static int numero = 1;
	private int id;
	private Color color;
	public Pedido(Color pColor){
		color = pColor;
		id = numero;
		numero++;
	}
	/**
	 * 
	 * @return El número del pedido
	 */
	public int getNumeroPedido(){
		return numero;
	}
	/**
	 * 
	 * @return El identificador
	 */
	public int getIdentificador(){
		return id;
	}
	/**
	 * 
	 * @return El color
	 */
	public Color getColor(){
		return color;
	}
}
