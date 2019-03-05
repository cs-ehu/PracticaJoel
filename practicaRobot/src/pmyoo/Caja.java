package pmyoo;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;

import ch.aplu.jgamegrid.Actor;
import ch.aplu.jgamegrid.GGBitmap;
import ch.aplu.robotsim.Target;

public class Caja extends Actor{

	private Target target;
	
	public Caja() {
		super(true, "escenarios/cajaGris.png");
		Point[] mesh = new Point[]{new Point(-10,1),new Point(-5,1), new Point(0,1),new Point(5,1), new Point(10,1)};
		target = new Target("escenarios/bar2.png", mesh);
	} 
	
	public Target getTarget(){
		return target;
	}
	
	public void cargada(){
		target.removeSelf();
	}
}