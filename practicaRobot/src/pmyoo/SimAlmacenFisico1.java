package pmyoo;

import ch.aplu.robotsim.*;
import ch.aplu.jgamegrid.*;
import java.awt.*;

public class SimAlmacenFisico1
{
  private RobotSeguidorLinea robot;
	
  public SimAlmacenFisico1()
  {
	  robot = new RobotSeguidorLinea();
  }

  public static void main(String[] args)
  {
    SimAlmacenFisico1 escenario = new SimAlmacenFisico1();
    escenario.getRobot().act();
  }
  
  public RobotSeguidorLinea getRobot(){
	  return robot;
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
	int ylinea = y -5;
	int xcasa = 52;
	int xcarga = 430;
	int xfila1 = 125;
	int yfila1 = 280;
    RobotContext.showStatusBar(30);
    RobotContext.showNavigationBar();
//    RobotContext.setStartPosition(20, y-5);
    RobotContext.setStartDirection(0);
    RobotContext.setStartPosition(xcasa, ylinea); 
//    RobotContext.setStartPosition(xcarga, y); 
    //RobotContext.setStartPosition(xfila1, yfila1); 
//    RobotContext.setStartDirection(90); //-90
    RobotContext.useBackground("escenarios/EscenarioAlmacen4lineas2.png");

    //casa borde
    RobotContext.useObstacle(bar(10, 60, Color.MAGENTA), 8, y-8); //obstacle
     

  }
}