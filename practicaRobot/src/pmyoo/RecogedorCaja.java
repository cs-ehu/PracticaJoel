package pmyoo;


import java.awt.Point;

import ch.aplu.jgamegrid.*;
import ch.aplu.robotsim.*;

public class RecogedorCaja extends Actor{
	public static final int TIEMPO_ELEVADOR = 50;
	
	private Motor motor;
	private Actor caja2;
	private boolean primera;
	private boolean arriba;
	
	public RecogedorCaja(MotorPort port) {
		super(true, "escenarios/recogedorCaja.png");
		caja2 = new Actor("escenarios/cajaGris2.png");
		setDirection(270);
		motor = new Motor(port);
		primera = true;
		arriba = true;
	} 

	public Motor getMotor(){
		return motor;
	}
	
	public void elevar(){
		motor.forward();	
		Tools.delay(TIEMPO_ELEVADOR);
		for(int i=0; i < 9; i++){
			this.rotate(new Point(430, 90), 10);
			Tools.delay(40);
		}
		motor.stop();
		arriba = true;
	}
	
	public void bajar(){
		motor.backward();
		Tools.delay(TIEMPO_ELEVADOR);
		for(int i=0; i < 9; i++){
			this.rotate(new Point(430, 90), -10);
			Tools.delay(40);
		}
		motor.stop();
		arriba = false;
	}
	
	public boolean getEstado(){
		return arriba;
	}
	
	public void recogerCaja(Caja caja){
		caja.hide();
		if (primera){
			LegoRobot.getGameGrid().addActor(caja2, new Location(400, 70));
			primera = false;
		}
		else
			caja2.show();
	}
	
	public void dejarCaja(Caja caja){
		Location loc = new Location(450,447);
		caja.setLocation(loc);
//		caja.rotate(new Point(450,450), 90);
//		Tools.delay(40);
		caja.show();
		caja2.hide();
		Tools.delay(100);
	}
}