package pmyoo;

import java.awt.Color;

import ch.aplu.robotsim.*;
/**
 * Es una clase que crea un robot de tipo LegoRobot, 
 * le añade unos motores y unos sensores para que siga el borde de un color y busque la fila de un color
 * @author Joel Villalobos
 * @version 2017.06.04
 */
public class RobotSeguidorLinea implements TouchListener{
	private LegoRobot robot;
	private Motor motorD;
	private Motor motorI;
	private ColorSensor sensorColor;
	private TouchSensor sensorContacto;
	public RobotSeguidorLinea(){
		robot = new LegoRobot();
		
		motorD = new Motor(MotorPort.A);
		robot.addPart(motorD);
		
		motorI = new Motor(MotorPort.B);
		robot.addPart(motorI);
		
		sensorColor = new ColorSensor(SensorPort.S1);
		robot.addPart(sensorColor);
		
		sensorContacto = new TouchSensor(SensorPort.S2);
		robot.addPart(sensorContacto);
		sensorContacto.addTouchListener( this );
	}
	/**
	 * 
	 * @return El robot creado de tipo LegoRobot
	 */
	public LegoRobot getLegoRobot(){
		return robot;
	}
	/**
	 * 
	 * @return El motor de la rueda derecha
	 */
	public Motor getMotorD(){
		return motorD;
	}
	/**
	 * 
	 * @return El motor de la rueda izquierda
	 */
	public Motor getMotorI(){
		return motorI;
	}
	/**
	 * 
	 * @return El sensor de color
	 */
	public ColorSensor getColorSensor(){
		return sensorColor;
	}
	/**
	 * 
	 * @return El sensor de contacto
	 */
	public TouchSensor getTouchSensor(){
		return sensorContacto;
	}
	/**
	 * 
	 * @param velocidad Cambia la velocidad
	 */
	public void setVelocidad(int velocidad){
		motorD.setSpeed(velocidad);
		motorI.setSpeed(velocidad);
	}
	/**
	 * Los dos motores van hacia adelante de manera que el robot va hacia adelante
	 */
	public void adelante(){
		motorD.forward();
		motorI.forward();
	}
	/**
	 * Los dos motores van hacia atras de manera que el robot va hacia atras
	 */
	public void atras(){
		motorD.backward();
		motorI.backward();
	}
	/**
	 * Los dos motores se paran de manera que el robot se para
	 */
	public void parar(){
		motorD.stop();
		motorI.stop();
	}
	/**
	 * 
	 * @return El color detectado por el sensor de color
	 */
	public Color getColor(){ 
		int[] naranja = {244,255, 192, 212 , 0 , 15 };
		Color color = sensorColor.getColor();
		if ( ColorSensor.inColorCube(color, naranja ) )
			return new Color(254,202,0);
		//else {  
		    return color;
		//}

    }
	/**
	 * El robot pone en marcha un motor hacia adelante y otro hacia atras de manera que el 
	 * robot ira girando cierto tiempo hasta dar media vuelta
	 */
	public void darLaVuelta(){
		motorD.forward();
		motorI.backward();
		Tools.delay(1350);
		parar();
	}
	/**
	 * El robot pone en marcha el motor derecho hacia adelante y el izquierdo hacia atras
	 * de manera que el robot girara a la izquierda
	 * @param tiempo gira hacia la izquierda el tiempo definido
	 */
	public void girarI(int tiempo){
		motorD.forward();
		motorI.backward();
		Tools.delay(tiempo);
		parar();
	}
	/**
	 * El robot pone en marcha el motor izquierdo hacia adelante y el derecho hacia atras
	 * de manera que el robot girara a la derecha
	 * @param tiempo gira hacia la derecha el tiempo definido
	 */
	public void girarD(int tiempo){
		motorD.backward();
		motorI.forward();
		Tools.delay(tiempo);
		parar();
	}
	/**
	 * 
	 * @param color El color que debe seguir el robot
	 * @return True si el color es blanco o el mismo que el detectado por el sensor de color
	 */
	public boolean debeSeguirBorde(Color color){
		if (sensorColor.getColor().equals(Color.WHITE) || getColor().equals(color)){
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * Mientras el color que deba seguir sea blanco o el color detectador por el sensor de color
	 * repetira el siguiente proceso:
	 * si el color es blanco girará a la izquierda y si no, es decir, si es el detectado por el
	 * sensor de color girará a la derecha
	 * De esta manera el robot seguira el borde derecho de el color
	 * @param color El color que tiene que seguir el robot
	 */
	public void seguirBorde(Color color){
		while (debeSeguirBorde(color)){
			if (sensorColor.getColor().equals(Color.WHITE)){
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
	/**
	 * Mientras el color detectado por el sensor sea distinto que el color repetira este proceso:
	 * Ira hacia adelante un poco para y seguira el borde negro, cuando el color detectado sea
	 * el mismo que el color, el robot se parará, ya que ha encontrado la fila buscada
	 * @param color El color de fila que tiene que buscar
	 */
	public void  buscarFila (Color color){
		while(getColor().equals(color) == false){
			adelante();
            Tools.delay(30);
            seguirBorde(Color.BLACK);
		}
		
		parar();
	}
	/**
	 * El robot  seguira el borde negro hasta que el sensor de contacto sea presionado por
	 * el obstaculo que hay en casa, luego girará y avanzará un poco para aparcar el robot en casa
	 */
	public void irAcasa(){
		while(!sensorContacto.isPressed()){
			adelante();
	        Tools.delay(30);
	        parar();
			seguirBorde(Color.BLACK);
		}
		pressed(SensorPort.S2);
		released(SensorPort.S2);
	}
	public void act(){
		buscarFila(Color.YELLOW);
        darLaVuelta();
		irAcasa();
	}
	/**
	 * Va hacia atras un determinado tiempo
	 */
	public void pressed(SensorPort port) {
			parar();
	        atras();
	        Tools.delay(200);
    }
	/**
	 * Da media vuelta y avanza un poco
	 */
    public void released(SensorPort port) {
        parar();
    	darLaVuelta();
        adelante();
        Tools.delay(500);
        parar();
        
    }
}

