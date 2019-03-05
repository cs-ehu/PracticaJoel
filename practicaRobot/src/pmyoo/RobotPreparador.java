package pmyoo;

import java.awt.Color;
import ch.aplu.robotsim.*;
/**
 * Crea un robot de tipo RobotSeguidorLinea y le añade un sensor de distancia y un recogedor de caja,
 * de manera que pueda detectar objetos segun la distancia y recoger cajas
 * @author Joel Villalobos
 * @version 2017.06.04
 */
public class RobotPreparador extends RobotSeguidorLinea implements UltrasonicListener {
	private UltrasonicSensor sensorDistancia;
	private RecogedorCaja recogedorDeCaja;
	private Direccion direccion;
    private Tarea tarea;
    private AlmacenGestor almacenGestor;
    private Caja caja;
	public RobotPreparador(){
		super();
		
		sensorDistancia = new UltrasonicSensor(SensorPort.S3);
		 getLegoRobot().addPart(sensorDistancia);
		sensorDistancia.setBeamAreaColor(Color.GREEN);
		//sensorDistancia.setDirection(-180);
		sensorDistancia.setProximityCircleColor(Color.DARK_GRAY);
		recogedorDeCaja = new RecogedorCaja(MotorPort.C);
		direccion = Direccion.ESTE;
		tarea = Tarea.EN_CASA;
		getLegoRobot().addPart(recogedorDeCaja.getMotor());
		
	}
	/**
	 * 
	 * @return El sensor de distancia
	 */
	public UltrasonicSensor getUltrasonicSensor(){
		return sensorDistancia;
	}
	/**
	 * 
	 * @return El recogedor de caja
	 */
	public RecogedorCaja getRecogedorCaja(){
		return recogedorDeCaja;
	}
	/**
	 * 
	 * @return La direccion del robot
	 */
	public Direccion getDireccion(){
		return direccion;
	}
	/**
	 * 
	 * @return La tarea asignada al robot
	 */
	public Tarea getTarea(){
		return tarea;
	}
	/**
	 * 
	 * @param pDireccion Cambia la direccion
	 */
	public void setDireccion(Direccion pDireccion){
		direccion = pDireccion;
	}
	/**
	 * 
	 * @param pTarea Cambia la tarea asignada
	 */
	public void setTarea(Tarea pTarea){
		tarea = pTarea;
	}
	/**
	 * 
	 * Da media vuelta y cambia la direccion
	 */
	public void darLaVuelta(){
		super.darLaVuelta();
		int grados;
        grados = (getDireccion().getValor() + 180) % 360; 
        if ( grados == 0 )
        {
            direccion = Direccion.ESTE;
        }
        else if( grados == 90  )
        {
            direccion = Direccion.SUR;
        }
        else if( grados == 180 )
        {
            direccion = Direccion.OESTE;
        }
        else
        {
            direccion = Direccion.NORTE;
        }  
	}
	/**
	 * Si la tarea es EN_CASA o CARGANDO no debe seguir borde, si no debe seguir el borde del color
	 * @param color El color que debe seguir el robot
	 */
	public boolean debeSeguirBorde(Color color){
		if (tarea == Tarea.EN_CASA || tarea == Tarea.CARGANDO){
			return false;
		}
		else{
			return super.debeSeguirBorde(color);
		}
	}
	/**
	 * Buscará la fila del color indicado y si la tarea es DEJANDO_CAJA la cambiará a BUSCANDO_FILA
	 * @param color El color de fila que tiene que buscar
	 */
	public void buscarFila(Color color){
		super.buscarFila(color);
		if (tarea != Tarea.DEJANDO_CAJA){
			setTarea(Tarea.BUSCANDO_FILA);
		}
	}
	/**
	 * El robot girará segun la direccion que tenga y cambiará la direccion una vez girado
	 */
	public void girar(){
		//girarD(675);
        if (getDireccion() == Direccion.ESTE)
        {
            girarI(600);
            setDireccion(Direccion.NORTE);
        }
        else if(getDireccion() == Direccion.OESTE)
        {
            girarD(600);
            setDireccion(Direccion.NORTE);
        }
        else if(getDireccion() == Direccion.SUR)
        {
        	adelante();
        	Tools.delay(20);
            girarI(600);
            setDireccion(Direccion.ESTE);
        }
	}
	/**
	 * Buscara una caja, para ello primero pondrá la tarea a BUSCANDO_CAJA, luego buscará la fila,
	 * una vez encontrada la fila girará, cambiará la tarea a BUSCANDO_CAJA, se adelantara un poco
	 * y seguira el borde del color de la fila hasta que el sensor detecte un obstaculo
	 * a cierta distancia
	 * @param color El color de la fila de la caja que tiene que buscar
	 */
	public void buscarCaja(Color color){
		setTarea(Tarea.BUSCANDO_FILA);
		buscarFila(color);
		girar();
		setTarea(Tarea.BUSCANDO_CAJA);
		adelante();
		Tools.delay(200);
		parar();
		while(getUltrasonicSensor().getDistance() > 30 || getUltrasonicSensor().getDistance() == -1){
			if (debeSeguirBorde(color)){
				if (getColorSensor().getColor().equals(Color.WHITE)){
					adelante();
					Tools.delay(50);
		            girarI(100);
				}
				else{
					adelante();
					Tools.delay(50);
		            girarD(100);
				}
			}	
		}
		parar();
		
	}
	@Override
	public void far(SensorPort arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	/**
	 * Cambia la tarea de BUSCANDO_CAJA a CARGANDO
	 */
	public void near(SensorPort arg0, int arg1) {
		if(tarea == Tarea.BUSCANDO_CAJA){
			setTarea(Tarea.CARGANDO);
			//no se si falta algo
		}
		
	}
	/**
	 * Cambaiará la tarea a CARGANDO, bajara el  recogedor de caja, 
	 * @param color El color de la fila de la caja que tiene que cargar, a caja la asignará 
	 * la primera caja a recuperar y el recogedor de caja recogerá esa caja
	 */
	public void cargarCaja(Color color){
		setTarea(Tarea.CARGANDO);
		recogedorDeCaja.bajar();
		caja = almacenGestor.recuperarPrimeraCaja(color);
		recogedorDeCaja.recogerCaja(caja);
	}
	/**
	 * Levantará el recogedor de caja y cambiará la tarea a LIBRE y dejará la caja
	 */
	public void  descargarCaja(){
		recogedorDeCaja.elevar();
		setTarea(Tarea.LIBRE);
		recogedorDeCaja.dejarCaja(caja);
	}
	/**
	 * Cambiará la tarea a DEJANDO_CAJA seguirá el borde de color mientras el color detectado
	 * sea distinto del negro, una vez sea encontrado el negro avanzará un poco hasta 
	 * encontrarse con otro color, girará y buscara la fila del color del almacen y
	 * descargará la caja
	 * @param color El color de la fila de la caja que tiene que dejar
	 */
	public void  dejarCaja(Color color){
		setTarea(Tarea.DEJANDO_CAJA);
		while(!getColor().equals(Color.BLACK)){
			adelante();
            Tools.delay(30);
            parar();
			seguirBorde(color);
		}
		adelante();
		Tools.delay(300);
		parar();
		girar();
		buscarFila(new Color(254,202,0));
		super.girarD(130);
		Tools.delay(50);
		descargarCaja();
	}
	/**
	 * Buscará la caja, la cargará, dará la vuelta, irá a dejar la caja al almacen, se echará
	 * un poco hacia atras y dará media vuelta, una vez hecho esto, la caja depositada en el
	 * almacen se borrará
	 * @param color El color de la fila donde se encuentra el pedido que tiene que preparar
	 */
	public void prepararPedido(Color color){
		buscarCaja(color);
		cargarCaja(color);
		darLaVuelta();
		getUltrasonicSensor().eraseBeamArea();
		dejarCaja(color);
		atras();
		Tools.delay(400);
		parar();
		darLaVuelta();
		caja.removeSelf();
		
	}
	/**
	 * Cuando la tarea sea LIBRE irá a casa
	 */
	public void irAcasa(){
		if (tarea == Tarea.LIBRE){
			super.irAcasa();
		}
	}
	/**
	 * Si la tarea es LIBRE cambiará la tarea a EN_CASA y hará pressed
	 */
	public void  pressed(SensorPort port){
		if (tarea == Tarea.LIBRE){
			setTarea(Tarea.EN_CASA);
		}
		super.pressed(port);
	}
	/**
	 * a almacenGestor le asignara un gestor de tipo AlmacenGestor
	 */
	public void setGestor(AlmacenGestor gestor) {
		almacenGestor = gestor;
	}
	/**
	 * 
	 * @return La caja
	 */
	public Caja getCaja(){
		return caja;
	}
}
