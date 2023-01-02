import java.awt.Color;
import java.awt.Graphics;

import Characters.Player;

public class Event {
	/**
	 * In der Klasse Event, ist die Logik für spielevents enthalten. Hier wird abgefragt, ob aktuell Events stattfinden und
	 * diese werden dann ausgeführt. Die Klasse Funtioniert über statische Methoden und Aufrufe. 
	 */
	//Menu Switches Battle
	private static boolean selectFight			=	false;
	private static boolean selectFlee			=	false;
	private static boolean selectItems			=	false;
	
	//Event Switches
	private static boolean l1_startEvent 		= 	true;
	private static boolean l1_potionOnField 	= 	true;
	private static boolean l1_shopKey 			= 	false;
	private static boolean l1_battle			= 	true;
	private static long timer1					= 	0;
	private static long timer2					= 	0;
	/**
	 * 
	 * @param cvs :Leinwand
	 * @param g	  :Graphics
	 */
	public static final void checkEvents(Leinwand cvs, Graphics g ) {
		
		if(l1_startEvent) 
		{
			initEvents(cvs, g);
			l1_startEvent = false;
		}
		short x = cvs.getPlayer().getPosX();
		short y = cvs.getPlayer().getPosY();
		
		if(playerOnField( 1, 1, x, y ) && l1_potionOnField ) 
		{
			addItem_l1(cvs, g);
		}
		
		if(playerOnField( 1,3,x,y ) && l1_battle) {
			
			if(!cvs.isBattle) {
				cvs.isBattle = true;
				cvs.isInGame = false;
				Music.getSound_Field().stop();
				Music.getSound_BattleEasy().start();
			}
			battle_l1(cvs, g);
		}
	}
	
	//TODO
	
	private static final void initEvents(Leinwand cvs, Graphics g) {
		cvs.getFields()[1][1].getTile().setLayer3(Helper.readImage("res/heiltrank.png"));
	}
	/**
	 * Fragt ab, ob der Spieler auf dem Feld innerhalb eines Leves befindet : boolean
	 * @param i
	 * @param j
	 * @param playerPosX
	 * @param playerPosY
	 * @return boolean
	 */
	private static final boolean playerOnField(int i, int j, short playerPosX, short playerPosY ) {
		return (playerPosX > (i * 32) -5 && playerPosX < ((i+1)*32)+5 && playerPosY > (j * 32) -5 && playerPosY < ((j+1)*32)+5);
	}
	/**
	 * Fügt dem Spieler an der Spielfeldpostion ein Heiltrank hinzu
	 * @param panel
	 * @param g
	 */
	private static final void addItem_l1(Leinwand panel, Graphics g) {
			
		if(timer1 == 0) {
			panel.isEvent = true;
			l1_shopKey = true;
			panel.getFields()[1][1].getTile().setLayer3(null);
			panel.getPlayer().addItem(panel.getItem("Heiltrank klein"));
			Music.getSe_openMenu().start();
			timer1++;
		}
		if(panel.isEvent) 
		{
			panel.drawTextField(g, "Du hast einen " + panel.getItem("Heiltrank klein").getName() + "gefunden");
		}
		else 
		{
			panel.isEvent = false;
			l1_potionOnField = false;
		}
	}
	/**
	 * BattleEvent in welchem der Kampf im Spiel implementiert ist, soll später variabel gestatltete werden und alle Kämpfe abbilden können
	 * @param cvs
	 * @param g
	 */
	private static final void battle_l1(Leinwand cvs, Graphics g) {
		
		Player player = cvs.getPlayer();
		cvs.drawBattleBack(g, 1);
		g.setColor(new Color( 0, 0, 0, 50));
		g.fillRect(20, 480, 580, 120);
		g.setColor(Color.white);
		g.drawString("HP", 140, 500);
		g.drawString("EP", 140, 520);
		cvs.drawHpBar(g,cvs.getPlayer().getHP() , cvs.getPlayer().getMaxHP(), (short)160, (short) 490);
		cvs.drawEpBar(g,cvs.getPlayer().getEP() , cvs.getPlayer().getMaxEP(), (short)160, (short) 510);
		cvs.drawPlayerImage(g, 20, 450);
		
		//ENEMY
		if(timer1<400) 
		{
			cvs.drawEnemyImage(g, 250, 150);
			cvs.drawHpBar(g,(byte)100 , (byte)100, (short)260, (short) 100);
		}
		
		g.setColor(Color.white);
		if(selectFight)
		{
			if(timer1 < 800) {
				cvs.drawTextBattle(g, "Ismail greift an und verursacht tödlichen Schaden");
				timer1++;
			}
			else
			{
				cvs.isBattle 		= 	false;
				cvs.isInGame 		= 	true;
				cvs.selectedEntry 	= 	0;
				l1_battle 	 		=	false;
				Music.getSound_BattleEasy().stop();
				Music.getSound_Field().start();
				timer1 = 0;
			}
		}
		else if(selectItems) 
		{
			for(int i = 0; i < player.getInventorySize(); i++) 
			{
				g.drawString(player.getInventory().get(i).getCount() + " X  " + player.getInventory().get(i).getName(), 350, (i+25) * 20);
			}
			
			g.setColor(new Color(100, 100, 150, 100));
			g.fillRect(340, 485 + ( cvs.selectedEntry * 20), 150, 20);
			
		}
		else if(selectFlee)
		{
			cvs.isBattle 		= 	false;
			cvs.isInGame 		= 	true;
			cvs.selectedEntry 	= 	0;
			l1_battle 	 		=	false;
			Music.getSound_BattleEasy().stop();
			Music.getSound_Field().start();
		}
		else 
		{
			g.drawString("Fight", 350, 500);
			g.drawString("Item",  350, 520);
			g.drawString("Flee",  350, 540);
			
			g.setColor(new Color(100, 100, 150, 100));
			
			if(cvs.selectedEntry == 0) 
			{
				g.fillRect(340, 485, 100, 20);
			}
			else if(cvs.selectedEntry == 1) 
			{
				g.fillRect(340, 505, 100, 20);
			}
			else if(cvs.selectedEntry == 2) 
			{
				g.fillRect(340, 525, 100, 20);
			}
		}
		
		if(cvs.getPlayer().getHP() == 0)
			cvs.isGameOver = true;
			
	}
	/**
	 * Gibt ausgewaehltes Element im Kampfmenu zurück : boolean
	 * @return
	 * boolean
	 */
	public static final boolean getSelectFight() {
		return selectFight;
	}
	/**
	 * Gibt ausgewaehltes Element im Kampfmenu zurück : boolean
	 * @return
	 * boolean
	 */
	public static final boolean getSelectFlee() {
		return selectFlee;
	}
	/**
	 * Gibt ausgewaehltes Element im Kampfmenu zurück : boolean
	 * @return
	 * boolean
	 */
	public static final boolean getSelectItems() {
		return selectItems;
	}
	/**
	 * Gibt ausgewaehltes Element im Kampfmenu zurück : boolean
	 * @return
	 * boolean
	 */
	public static final void setSelectFlee() {
		selectFlee = true;
	}
	/**
	 * Setzt ausgewaehltes Element im Kampfmenu auf true
	 */
	public static final void setSelectItems(boolean b) {
		selectItems = b;
	}
	/**
	 * Setzt ausgewaehltes Element im Kampfmenu  : boolean
	 * @param
	 * boolean
	 */
	public static void setSelectFight(boolean b) {
		selectFight = b;
	}

	
}//class
