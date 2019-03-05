package pmyoo;

import ch.aplu.robotsim.*;
import ch.aplu.jgamegrid.*;
import java.awt.*;

public class SimAlmacenFisico3
{
  private RobotPreparador robot;
  private static Stock stock;
	
  public SimAlmacenFisico3()
  {
	  robot = new RobotPreparador();
  }

  public static void main(String[] args)
  {
    SimAlmacenFisico3 escenario = new SimAlmacenFisico3();
    anadirCajasEscenario();
    RecogedorCaja recogedor = escenario.getRobot().getRecogedorCaja();
    LegoRobot.getGameGrid().addActor(recogedor, new Location(430, 60));

/*
    RecogedorCaja recogedor = new RecogedorCaja(MotorPort.C);
    LegoRobot.getGameGrid().addActor(recogedor, new Location(430, 60));
 

    escenario.getRobot().getLegoRobot().addPart(recogedor.getMotor());
    recogedor.bajar();
    Tools.delay(2000);
    recogedor.elevar();
 */  
    escenario.getRobot().act();
  }
  
  public RobotPreparador getRobot(){
	  return robot;
  }
  
  public static Stock getStock(){
	  return stock;
  }
  
  public static void anadirCajasEscenario(){
	  int x = 122;
	  int y = 285;
	  int i = 0;
	  for(Estanteria estanteria: stock.getEstanterias()){
		  int cx = x + 75*i;
		  int j = 0;
		  for(Caja caja: estanteria.getCajas()){
			    int cy = y - 20*j;
	    		LegoRobot.getGameGrid().addActor(caja, new Location(cx, cy));
	    		j++;
		  }
		  i++;
	  }
  }
  
  
  public static void actualizarStock(Color[] colores, int numCajas){
	    // cajas son actores y tienen target
	    int x = 122;
	    int y = 285;
	  	stock = new Stock(colores);
	    for(int i = 0; i < colores.length; i++){
	    	int cx = x + 75*i; 
	    	for(int j = 0; j < numCajas; j++){
	    		int cy = y - 20*j;
	    		Caja caja = new Caja();
	    		stock.anadirCaja(colores[i], caja);
	    	    RobotContext.useTarget(caja.getTarget(), cx, cy+5);   		
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
//    RobotContext.setStartPosition(xfila1, yfila1); 
//    RobotContext.setStartDirection(90); //-90
    RobotContext.useBackground("escenarios/EscenarioAlmacen4lineas2.png");

    //casa borde
    RobotContext.useObstacle(bar(10, 60, Color.MAGENTA), 8, y-8); //obstacle

    //visor elevador
    RobotContext.useObstacle(bar(80, 2, Color.BLACK), 400, 20);
    RobotContext.useObstacle(bar(2, 90, Color.BLACK), 360, 65);
    RobotContext.useObstacle(bar(80, 2, Color.BLACK), 400, 110);
    RobotContext.useObstacle(bar(2, 90, Color.BLACK), 440, 65);
 
    actualizarStock(new Color[]{new Color(253,10,0), new Color(36,255,0), Color.YELLOW, Color.BLUE}, 3);
     

  }
}