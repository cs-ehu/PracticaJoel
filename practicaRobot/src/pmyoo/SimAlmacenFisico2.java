package pmyoo;

import ch.aplu.robotsim.*;
import ch.aplu.jgamegrid.*;
import java.awt.*;

public class SimAlmacenFisico2
{
  private RobotPreparador robot;
	
  public SimAlmacenFisico2()
  {
	  robot = new RobotPreparador();
  }

  public static void main(String[] args)
  {
    SimAlmacenFisico2 escenario = new SimAlmacenFisico2(); 
    RecogedorCaja recogedor = escenario.getRobot().getRecogedorCaja();
    LegoRobot.getGameGrid().addActor(recogedor, new Location(430, 60));
    escenario.getRobot().act();
  }
  
  public RobotPreparador getRobot(){
	  return robot;
  }
  
  
  public static void actualizarStock(){
	    // cajas son target
	    Point[] mesh = new Point[]{new Point(-10,5),new Point(-5,5), new Point(0,5),new Point(5,5), new Point(10,5)};
	    for(int i = 0; i < 4; i++){
	    	int x = 122 + 75*i;
	    	for(int j = 0; j < 2; j++){
	    		int cy = 247 + 20*j;
	    		GGBitmap caja = bar(20, 10, new Color(125,125,125));
	    	    RobotContext.useTarget(caja, mesh, x, cy);   		
	    	}
	    }
  }
  
  // ------------------ Environment --------------------------
  private static GGBitmap bar(int width, int length, Color color)
  {
    GGBitmap bm = new GGBitmap(width, length);
    bm.setPaintColor(color);
    bm.fillRectangle(new Point(0, 0), new Point(width - 1, length - 1));
/*    if (color == Color.WHITE){
    	bm.setPaintColor(Color.BLACK);
    	bm.drawRectangle(new Point(0, 0), new Point(width - 1, length - 1));
    }
    */
    return bm;
  }

  static
  {
	int y = 447;
	int ylinea = y -3;
	int xcasa = 53;
	int xcarga = 430;
	int xfila1 = 124;
	int yfila1 = 320;
    RobotContext.showStatusBar(30);
    RobotContext.showNavigationBar();
//    RobotContext.setStartPosition(20, y-5);
    RobotContext.setStartDirection(0);
    RobotContext.setStartPosition(xcasa, ylinea); 
//    RobotContext.setStartPosition(xcarga, y); 
//    RobotContext.setStartPosition(xfila1, yfila1); 
//    RobotContext.setStartDirection(-90); //-90
    RobotContext.useBackground("escenarios/EscenarioAlmacen4lineas2.png");

    //casa borde
    RobotContext.useObstacle(bar(10, 60, Color.MAGENTA), 8, y-8); //obstacle
    
    //visor elevador
    RobotContext.useObstacle(bar(80, 2, Color.BLACK), 400, 20);
    RobotContext.useObstacle(bar(2, 90, Color.BLACK), 360, 65);
    RobotContext.useObstacle(bar(80, 2, Color.BLACK), 400, 110);
    RobotContext.useObstacle(bar(2, 90, Color.BLACK), 440, 65);

    actualizarStock();

    
     

  }
}