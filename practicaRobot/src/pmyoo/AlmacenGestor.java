package pmyoo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * Crea un gestor de almacen donde se encuentra un stock y un robot con ciertos pedidos
 * @author Joel Villalobos
 * @version 2017.06.04
 */
public class AlmacenGestor{
	private ArrayList<Pedido> listaPedidos;
	private RobotPreparador robot;
	private Stock stock;
	private Pedido pedido;
	public AlmacenGestor(Stock pStock, RobotPreparador pRobot ){
		robot = pRobot;
		stock = pStock;
		listaPedidos = new ArrayList<Pedido>();
	}
	/**
	 * Crea un pedido nuevo y lo añade auna lista de pedidos
	 * @param color El color de la fila del pedido que recibe
	 */
	public void recibePedido(Color color){
		Pedido pedido = new Pedido(color);
		listaPedidos.add(pedido);
	}
	/**
	 * Crea una lista de pedidos de manera que a medida que prepare un pedido lo borre de la lista 
	 * de pedidos y al terminar se vaya a casa
	 */
	public void despacharPedidos() {     
        Iterator<Pedido> iterador = listaPedidos.iterator();
        while( iterador.hasNext()){
            pedido = iterador.next();
            iterador.remove();
            robot.prepararPedido(pedido.getColor());    
           
        }
        robot.irAcasa();
    }
     /**
      * 
      * @param color El color de la fila de la primera caja a recuperar
      * @return La primera caja recuperada del stock
      */
    public Caja recuperarPrimeraCaja(Color color){
        return stock.recuperarPrimeraCaja(color);
    }
    /**
     * Sirve la caja
     * @param Caja La caja que se va a servir
     */
    public void cajaServida(Caja Caja){   
        stock.cajaServida( pedido.getColor() , Caja);
        Caja.cargada();
    }
    /**
     * 
     * @return La lista de los pedidos
     */
    public ArrayList<Pedido> getPedidos(){
    	return listaPedidos;
    }
    /**
     * 
     * @return El pedido
     */
    public Pedido getUltimoPedido(){
    	return pedido;
    }
}
