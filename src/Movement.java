import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
/**
* <p>Diese Klasse implementiert den KeyListener und ist somit zuständig für die navigation aller spielelemente 
* <P>Konstruktoren: 
*
* @param panel Leinwand
* 
* @author dennisb/ismaila
*/
public class Movement implements KeyListener {
  /**
   * Enthält alle wichtigen Elemente die bewegt werden müssen
   */
  private Leinwand panel;
  /**
   * 
   * @param panel : Leinwand
   */
  public Movement(Leinwand panel) {
	  this.panel = panel;
  } 
  /**
   * Übersetzt pixel in feldposition als Ganzzahl
   * @return int
   */
  private int getFieldX() {
	  return panel.getFieldCoordinateX(panel.getPlayer().getPosX());
  }
  /**
   * Übersetzt pixel in feldposition als Ganzzahl
   * @return int
   */
  private int getFieldY() {
	  return panel.getFieldCoordinateY(((short)(panel.getPlayer().getPosY())));
  }
  /**
   * Prüft ob ein feld neben einem gewählten feld(benötigt ganzzahlkoordinaten) passierbar ist
   * @param fieldX
   * @param fieldY
   * @return boolean
   */
  private boolean isRightPassable(int fieldX, int fieldY ) {
		  return (panel.getFields()[fieldX + 1][fieldY+1].getTile().getPassable());
  }
  /**
   * Prüft ob ein feld neben einem gewählten feld(benötigt ganzzahlkoordinaten) passierbar ist
   * @param fieldX
   * @param fieldY
   * @return boolean
   */
  private boolean isLeftPassable(int fieldX, int fieldY ) {
		  return (panel.getFields()[fieldX][fieldY].getTile().getPassable());
  }
  /**
   * Prüft ob ein feld neben einem gewählten feld(benötigt ganzzahlkoordinaten) passierbar ist
   * @param fieldX
   * @param fieldY
   * @return boolean
   */
  private boolean isUpPassable(int fieldX, int fieldY ) {
	  return (panel.getFields()[fieldX][fieldY].getTile().getPassable());
  }
  /**
   * Prüft ob ein feld neben einem gewählten feld(benötigt ganzzahlkoordinaten) passierbar ist
   * @param fieldX
   * @param fieldY
   * @return boolean
   */
  private boolean isDownPassable(int fieldX, int fieldY ) {
	  return (panel.getFields()[fieldX][fieldY+1].getTile().getPassable());
  }
  
  /**
   * Methode zum Prüfen welche Taste gedrückt wurde. Eventswitches und Auswahlswitches zum zeichnen werden hier verändert
   */
  @Override
  public void keyPressed(KeyEvent e) {
	  
	  if( !panel.isEvent && !panel.isInventory && !panel.isBattle) 
	  {
		  //
		  //KEY RIGHT
		  //
		  if(e.getKeyCode() == KeyEvent.VK_RIGHT) 
		  {
			  if(panel.isInGame)
			  {
				  if( panel.getPlayer().getPosX() < panel.getMapSizeX() - 32 )
				  {
					  if( isRightPassable(getFieldX(), getFieldY()) || (panel.getPlayer().getPosX() % 32) < 16) 
					  {
						  panel.getPlayer().moveRight(panel.getPlayer().getSpeed());
						  panel.getPlayer().setDirection((byte)2);
						  panel.getPlayer().makeAnimStep();
					  }
				  }
			  }
		  }
		  //
		  //KEY LEFT
		  //
		  if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			  
			  if( panel.isInGame) 
			  {
				  if( isLeftPassable(getFieldX(), getFieldY()) || (panel.getPlayer().getPosX() % 32) > 16 ) 
				  {
					  panel.getPlayer().moveLeft(panel.getPlayer().getSpeed());
					  panel.getPlayer().setDirection((byte)1);
					  panel.getPlayer().makeAnimStep();
				  }
			  }
		  }
		  //
		  //KEY UP
		  //
		  if(e.getKeyCode() == KeyEvent.VK_UP) 
		  {
			  if( panel.isMenu ) 
			  {
				  panel.selectedEntry = 0;
			  }
			  else if( panel.isInGame )
			  {
				  if( panel.getPlayer().getPosY() > 5) 
				  {
					  if( isUpPassable(getFieldX(), getFieldY()) ) 
					  {
						  panel.getPlayer().moveUp(panel.getPlayer().getSpeed());
						  panel.getPlayer().setDirection((byte)3);
						  panel.getPlayer().makeAnimStep();
					  }
				  }
			  }
		  }
		  //
		  //KEYDOWN
		  //
		  if(e.getKeyCode() == KeyEvent.VK_DOWN)
		  {
			  if( panel.isMenu ) 
			  {
				  panel.selectedEntry = 1;
			  }
			  else if(panel.isInGame )
			  {
				  if( panel.getPlayer().getPosY() < 590) 
				  {
					  if( isDownPassable(getFieldX(), getFieldY()) ) 
					  {
						  panel.getPlayer().moveDown(panel.getPlayer().getSpeed());
						  panel.getPlayer().setDirection((byte)0);
						  panel.getPlayer().makeAnimStep();
					  }
				  }
			  }
		  }
		  //
		  //KEY ESCAPE
		  //
		  if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			  Music.getSe_openMenu().start();
			  panel.isInventory = true;
		  }
		  //
		  //SPACE OR ENTER
		  //
		  if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {

			  if( panel.isMenu ) 
			  {
				  if( panel.selectedEntry == 0 ) 
				  {
					  panel.isInGame 	= true;
					  panel.isMenu 	= false;
					  Music.getSound_GameMenu().stop();
					  Helper.sleep(1);
					  Music.getSound_Field().start();
				  }
				  else if( panel.isInGame )
				  {
					  panel.isGameOver = true;
				  }
			  }  
		  } 
		  
	  }//!event
	  else if( panel.isEvent)
	  {
		  if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) 
		  {
			  panel.isEvent = false;
		  }
	  }
	  else if( panel.isInventory) 
	  {
		  if(e.getKeyCode() == KeyEvent.VK_ESCAPE) 
		  {
			  panel.isInventory = false;
			  panel.isInGame = true;
			  panel.selectedEntry = 0;
			  panel.selectedInventory = 0;
		  }
		  //TODO Nach einfügen von Waffen und Rüstung
		  /*
		  if(e.getKeyCode() == KeyEvent.VK_LEFT){
			  
			  if( cvs.selectedInventory == (byte)0) 
			  {
				  cvs.selectedInventory = 0;
				  cvs.selectedEntry = 0;
			  }
		  }
		  if(e.getKeyCode() == KeyEvent.VK_RIGHT){
			  
			  if( cvs.selectedInventory == (byte)1) 
			  {
				  cvs.selectedInventory = 1;
				  cvs.selectedEntry = 0;
			  }
		  }*/
		  if(e.getKeyCode() == KeyEvent.VK_UP){
			  
			  if( panel.selectedEntry > 0) 
			  {
				  panel.selectedEntry -= 1;
			  }
		  }
		  if(e.getKeyCode() == KeyEvent.VK_DOWN){
			  
			  if( panel.selectedEntry < panel.getPlayer().getInventorySize() - 1) 
			  {
				  panel.selectedEntry += 1;
			  }
		  }
		  if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER)
		  {
			  useItem();
		  }
	  }
	  else if(panel.isBattle) 
	  {
		  if(e.getKeyCode() == KeyEvent.VK_UP){
			  
			  if( panel.selectedEntry > 0) 
			  {
				  panel.selectedEntry -= 1;
			  }
		  }
		  if(e.getKeyCode() == KeyEvent.VK_DOWN){
			  
			  if( Event.getSelectItems() ) 
			  {
				  if(panel.selectedEntry < panel.getPlayer().getInventorySize() - 1)
					  panel.selectedEntry += 1;
			  }
			  else 
			  {
				  if(panel.selectedEntry < 2) 
					  panel.selectedEntry += 1;
			  }
		  }
		  if(e.getKeyCode() == KeyEvent.VK_ESCAPE) 
		  {
			  if(Event.getSelectItems()) {
				  Event.setSelectItems(false);
				  panel.selectedEntry = 0;
			  }
		  }
		  if(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER)
		  {
			  if(Event.getSelectItems()) 
			  {
				  useItem();
			  }
			  else if(panel.selectedEntry == 0) 
			  {
				  Event.setSelectFight(true);
			  }
			  else if(panel.selectedEntry == 1) 
			  {
				  Event.setSelectItems(true);
				  panel.selectedEntry = 0;
			  }
			  else if(panel.selectedEntry == 2) 
			  {
				  Event.setSelectFlee(); 
			  }
				 
		  }

	  }
	  
  }//keypressed
 
  /**
   * Methode die dem Spieler ein Item vom ingamemenu oder battlemenu heraus nutzen lässt und die items wie deren effekte handled
   */
  private void useItem() {
	  try 
	  {
		  if(panel.getPlayer().getInventorySize() > 0) {
			  panel.getPlayer().getInventory().get(panel.selectedEntry).executeItemEffect(panel.getPlayer());
			  Music.playSoundMenu(panel.getPlayer().getInventory().get(panel.selectedEntry).getSound());
			  panel.getPlayer().removeItem(panel.getPlayer().getInventory().get(panel.selectedEntry));
		  }
	  }
	  catch(Exception ex)
	  {
		  System.out.println("Fehler beim loeschen des Items oder der wiedergabe des itemsounds");
	  }
}
//was passiert wenn taste losgelassen, noch nicht genutzt
@Override
  public void keyReleased(KeyEvent e) {
     //moveStop();
  }
//wir haben mit keypressed gearbeitet
  @Override
  public void keyTyped(KeyEvent e) {
	  
  }
  
}//class
