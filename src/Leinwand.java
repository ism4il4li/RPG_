import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;
import Characters.Player;
import Items.Item;
/**
 * Zeichnet alle Elemente des Spiels auf den Screen und enthält alle Logik zum zeichnen
 * <p>Objekte der KLasse dienen zudem als Schnittstelle um auf viele andere Klassen zuzugreifen:
 * <p>Enthält den Player und kann diesen so Objektinstanzen anderer Klassen zugänglich machen.
 * <p>Konstruktoren:
 * @param level
 * @param player
 * @param items
 * 
 * @author ismaila/dennisb
 */
public class Leinwand extends JPanel{
	
	private static final long serialVersionUID = 1L;
	/**
	 * Switch für das Zeichnen des Inventarmenu : boolean
	 */
	protected boolean isInventory;
	/**
	 * Switch für das Zeichnen eines Kampfes : boolean
	 */
	protected boolean isBattle;
	/**
	 * Switch für das Zeichnen des aktuellen Levels in dem sich der Spieler befindet : boolean
	 */
	protected boolean isInGame;
	/**
	 * Switch für das beenden des SPieles : boolean
	 */
	protected boolean isGameOver;
	/**
	 * Switch für das Zeichnen des Hauptmenus : boolean
	 */
	protected boolean isMenu;
	/**
	 * Switch für das Unterbrechen des Zeichnen solange bestimmte Events laufen : boolean
	 */
	protected boolean isEvent;
	/**
	 * Switch für das Inventarmenu zum wählen des bereichs, items = 0, weapons = 1, armor = 2 : byte
	 */
	protected byte selectedInventory;
	/**
	 * Switch für die Ingame Menu navigation im inventarmenu auf dem Levelfeld sowie im battle : byte
	 */
	protected byte selectedEntry;
	/**
	 * Enthält den Hauptcharakter, also den steuerbaren Spieler : Player
	 */
	private Player player;
	/**
	 * enthält alle Felder des aktuellen Levels : Field[][]
	 */
	private Field[][] fields;
	/**
	 * Die gesamtgrösse der map in x-Richtung, des aktuellen levels : int
	 */
	private int mapSizeX;
	/**
	 * enthält alle Items um diese aus einem Objekt dieser Klasse zu erfragen : int
	 */
	private Item[] items;
	/**
	 * auswahlvariable für das Hauptmenu : int
	 */
	private int selectMenu;
	/**
	 * 
	 * @param level
	 * @param player
	 * @param items
	 */
	public Leinwand(Level level, Player player, Item[] items) {
		
		this.player 			= 	player;
		this.fields 			= 	level.getFields();
		this.isInventory		= 	false;
		this.isBattle  			= 	false;
		this.isInGame			=	false;
		this.isGameOver  		=	false;
		this.isMenu				=	true;
		this.selectedEntry  	=	0;
		this.selectedInventory 	=	0;
		this.mapSizeX			=	this.fields.length * 32;
		this.items				=	items;
		this.selectMenu			=	0;
	}
	
	/**
	 * Methode zum zeichnen aller Grafiken auf dem Screen
	 */
	@Override
	public void paint(Graphics g) {
		
		if(!this.isGameOver) 
		{
			if(isMenu) 
			{
				drawMenu(g);
			}
			else if(this.isInGame) 
			{
				g.clearRect(0, 0, 640, 640);
	
				for(int x = 0; x < fields.length; x++) 
				{
					for(int y = 0; y < fields[x].length; y++) 
					{
						drawField(fields[x][y], g);
					}
				}
				//player Zeichnen
				drawPlayer(g);
				//Draw events
				Event.checkEvents(this, g);
				//hp bar,ep bar,name
				drawHpBar(g, player.getHP(), player.getMaxHP(),(byte)10, (byte)10);
				drawPlayerName(g, player.getName());
				drawEpBar( g, player.getEP(), player.getMaxEP(), (byte)10, (byte)30 );

			}
			else if(isBattle) 
			{
				g.clearRect(0, 0, 640, 640);
				Event.checkEvents(this, g);
			}
			
			if( isInventory ) 
			{
				drawInventoryMenu(g);
			}
		}	
	}
	
	/**
	 * Methode zum zeichnen einer hp bar an position x und y
	 * @param g
	 * @param hp
	 * @param maxHP
	 * @param x
	 * @param y
	 */
	public void drawHpBar(Graphics g, byte hp, byte maxHP, short x, short y){
		
		if( hp > (maxHP / 2) ) 
		{
			g.setColor(new Color(255 - ((hp*2 > 255) ? 255 : (hp*2)), 255, 0, 200 ) );
			g.fillRect(x, y, hp, 15);
		}
		else if( hp <= (maxHP / 2) ) 
		{
			g.setColor(new Color(255, 255 - (int)(255/hp), 0, 200 ) );
			g.fillRect(x, y, hp, 15);
		}
	}
	
	/**
	 * Methode zum zeichnen des Hauptcharacters auf dem Spielfeld
	 * @param g
	 */
	private void drawPlayer(Graphics g ) {
		
		if( mapSizeX < (32 * 20) + 1 ) 
		{
			g.drawImage( player.getCharacterSprites()[player.getAnimStep()][player.getDirection()], player.getPosX(), player.getPosY(), null);
		}
		else
		{
			if(player.getPosX() > 270 && player.getPosX() < mapSizeX - 360) 
			{
				g.drawImage( player.getCharacterSprites()[player.getAnimStep()][player.getDirection()], 270, player.getPosY(), null);
			}
			else if(player.getPosX() < 270 ) 
			{
				g.drawImage( player.getCharacterSprites()[player.getAnimStep()][player.getDirection()], player.getPosX(), player.getPosY(), null);
			}
			else 
			{
				g.drawImage( player.getCharacterSprites()[player.getAnimStep()][player.getDirection()], player.getPosX()- 334, player.getPosY(), null);
			}
		}
	} 

	/**
	 * Methode zum zeichnen des Spielernamens auf dem Spielfeld
	 * @param g
	 * @param playerName
	 */
	private void drawPlayerName(Graphics g, String playerName) 
	{
		g.setColor(Color.black);
		g.drawString(playerName, 200 + 10, 25);
	}
	/**
	 * Methode zum zeichnen der EP//EnergiePunkte//Des Players an position x, y in Pixeln
	 * @param g
	 * @param ep
	 * @param maxEP
	 * @param x
	 * @param y
	 */
	public void drawEpBar(Graphics g, byte ep, byte maxEP, short x, short y) {
		
		if( ep > 0 ) 
		{
			g.setColor(new Color( 10, 20, 255, 200 ) );
			g.fillRect(x, y, ep, 15);
		}
	}
/**	
 * Zeichnet ein Textfeld mit dem text der übergeben wird
 * @param g
 * @param txt
 */
	public void drawTextField(Graphics g, String txt) {
		
		g.setColor(new Color(0, 0, 0, 150));
		g.fillRect(100, 450, 440 , 180 );
		g.setColor(Color.white);
		
		if(txt.length() < 40 ) 
		{
			g.drawString(txt, 110, 500);
		}
		else if(txt.length() >= 40 && txt.length() <= 80) 
		{
			//g.drawString(txt, 110, 500);
			//g.drawString(txt, 1289zkjdsfioj)
		}
	}
/**
 * Zeichnet das Main Menu	
 * @param g
 */
	private void drawMenu(Graphics g) {
		
		g.drawImage(Images.getMenu(), 0, 0, null);
		g.setColor(new Color(0, 0, 0, 100));
		switch(selectedEntry) 
		{
			case 0:
				g.fillRect( 120, 235, 350, 70);
				break;
			case 1:
				g.fillRect( 120, 315, 350, 70);
				break;
		}
	}
	/**
	 * Zeichnet das Inventarmenu auf wenn isIngame & isInventory;
	 * @param g
	 */
	public void drawInventoryMenu(Graphics g) {

		//Hintergrund
		g.setColor(new Color(0, 0, 0, 150));
		g.fillRect(0, 0, 640 , 640 );
		//AuswahlKasten
		
		//ItemKasten
		if(selectMenu == 0) 
		{
			g.setColor(new Color(100, 100, 150, 100));
			g.fillRect(10, 50, 170 , 500 );
		}
		else 
		{
			g.setColor(new Color(25, 13, 80, 40));
			g.fillRect(10, 50, 170 , 500 );
		}
		//Weapon Kasten
		if(selectMenu == 1) 
		{
			g.setColor(new Color(100, 100, 150, 100));
			g.fillRect(190, 50, 180 , 250 );
		}
		else 
		{
			g.setColor(new Color(25, 13, 80, 40));
			g.fillRect(190, 50, 180 , 250 );
		}
		if(selectMenu == 2) 
		{
			g.setColor(new Color(100, 100, 150, 100));
			g.fillRect(190, 270, 180 , 280 );
		}
		else
		{
			g.setColor(new Color(25, 13, 80, 40));
			g.fillRect(190, 270, 180 , 280 );
		}
		//Info Kasten
		g.setColor(new Color(25, 13, 80, 40));
		g.fillRect(380, 50, 250 , 500 );
		g.drawImage(Images.getPlayerImage(), 410, 90, null);
	
		drawHpBar(g, player.getHP(), player.getMaxHP(),(short)470, (short)55);
		drawEpBar( g, player.getEP(),(byte)100, (short)470, (short)75 );
		
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.PLAIN, 15));

		g.drawString("Item" + "  | " + "Anzahl", 40, 40);
		g.drawString("Waffe | Anzahl", 220, 40);
		g.drawString("Rüstung  |  Anzahl", 210 , 290);
		g.drawString(player.getName(), 430, 40);
		g.drawString("HP " + player.getHP() + "/" + player.getMaxHP(), 390, 70);
		g.drawString("EP " + player.getEP() + "/100" , 390, 90);
		
		for(int i = 0; i < player.getInventorySize(); i++) 
		{
			g.drawString(player.getInventory().get(i).getCount() + " X  " + player.getInventory().get(i).getName(), 20, (i+4) * 20);
		}
		
		if(player.getInventorySize() != 0) 
		{
			g.setColor(new Color(0 , 0, 0, 50) );
			g.fillRect(selectedInventory * 180 + 10, selectedEntry * 20 + 67, 160, 16);
		}
		
		//TODO Waffen und Rüstung
		
	}
	/**
	 * zeichnet die Hintergrundgrafik für den Kampf. Mit der Auswahlvariable i kann der entsprechende hintergrund gewählt werden
	 * <p>1 = Battleback grass
	 * <p>isBattle muss true sein
	 * @param g
	 * @param i
	 */
	public void drawBattleBack(Graphics g, int i) {
		g.drawImage(Images.getBattleBackground(i), 0, 0, null);
	}
	/**
	 * Zeichnet das Bild des SPielers in Menüs und im Kampf an posx, posy in pixeln
	 * @param g
	 * @param posX
	 * @param posY
	 */
	public void drawPlayerImage(Graphics g, int posX, int posY ) {
		g.drawImage(Images.getPlayerImage(), posX, posY, null);
	}
	/**
	 * Gibt den SPieler, die Hauptfigur zurück, wichtig für Objekte anderer Klassen
	 * @return Player
	 */
	public Player getPlayer() {
		return this.player;
	}
	/**
	 * Gibt die Felder des Aktuellen levels zurück, welches gezeichnet wird
	 * @return Field[][]
	 */
	public Field[][] getFields(){
		return this.fields;
	}
	/**
	 * Übersetzt die Pixelposition in die Feldposition als ganzzahl
	 * @param posX
	 * @return int
	 */
	 public int getFieldCoordinateX(short posX) {
		 return ( (int)(posX/32) );
	 }
	 /**
		 * Übersetzt die Pixelposition in die Feldposition als ganzzahl
		 * @param posX
		 * @return int
		 */
	 public int getFieldCoordinateY(short posY) {
		 return ((int)((posY+16)/32));
	 }
	 /**
	  * Gibt ein Item zurück durch übergabe des names
	  * @param itemName
	  * @return Item
	  */
	 public Item getItem(String itemName) {
		 for(Item i : this.items) {
			 if(i.getName().equalsIgnoreCase(itemName))
				 return i;
		 }
		 return null;
	 }
	/**
	 * <p>zeichnet das Bild eines Feindes auf den Battlebackground an der position x,y in Pixeln
	 * <p>erweiterbar durch auswahl des images
	 * @param g
	 * @param x
	 * @param y
	 */
	public void drawEnemyImage(Graphics g, int x, int y) {
		g.drawImage(Images.getEnemyImage(1), x, y, null);
	}
	/**
	 * Zeichnet text für unseren testkampf zur präsentation
	 * @param g
	 * @param txt
	 */
	public void drawTextBattle(Graphics g, String txt) {
		
		g.setColor(new Color(220, 33, 200));
		
		if(txt.length() < 100 ) 
		{
			g.setFont(new Font("Arial", Font.PLAIN, 30));
			g.drawString(txt, 20, 200);
		}
		else if(txt.length() >= 40 && txt.length() <= 80) 
		{
			//g.drawString(txt, 110, 500);
			//g.drawString(txt, 1289zkjdsfioj)
		}
	}
	/**
	 * Gibt die Grösse der map in pixeln zurück vom aktuellen level
	 * @return int
	 */
	public int getMapSizeX() {
		return this.mapSizeX;
	}
	
	private void drawField(Field field, Graphics g) {
		
		if(field != null)
		{
			
			if( mapSizeX <= 20 * 32) 
			{
				System.out.println(mapSizeX);
				g.drawImage( 	field.getTile().getLayer1(), 
								field.getPosX(), 
								field.getPosY(),
								null						);
		
				g.drawImage(	field.getTile().getLayer2(), 
								field.getPosX(), 
								field.getPosY(),
								null 						);
		
		
				g.drawImage(	field.getTile().getLayer3(), 
								field.getPosX(), 
								field.getPosY(),
								null 						);
				
			}
			else 
			{
				
				if( player.getPosX() > 270 && player.getPosX() < mapSizeX - 370  ) 
				{					
					g.drawImage( 	field.getTile().getLayer1(), 
									field.getPosX() - ( player.getPosX() - 272), 
									field.getPosY(),
									null						);
									
					g.drawImage(	field.getTile().getLayer2(), 
									field.getPosX() - ( player.getPosX() - 272), 
									field.getPosY(),
									null 						);
					
					g.drawImage(	field.getTile().getLayer3(), 
									field.getPosX() - ( player.getPosX() - 272), 
									field.getPosY(),
									null 						);
				}
				else if( player.getPosX() >= mapSizeX - 370 ) 
				{
					g.drawImage( 	field.getTile().getLayer1(), 
									//field.getPosX() - ( 312), 
									mapSizeX - field.getPosX(),
									field.getPosY(),
									null						);
	
					g.drawImage(	field.getTile().getLayer2(), 
									//field.getPosX()  - ( 312), 
									mapSizeX - field.getPosX(),
									field.getPosY(),
									null 						);
					
					g.drawImage(	field.getTile().getLayer3(), 
									//field.getPosX() - ( 312), 
									mapSizeX - field.getPosX(),
									field.getPosY(),
									null 						);
				}
				else
				{
					g.drawImage( 	field.getTile().getLayer1(), 
									field.getPosX(), 
									field.getPosY(),
									null						);
	
					g.drawImage(	field.getTile().getLayer2(), 
									field.getPosX(), 
									field.getPosY(),
									null 						);
					
					g.drawImage(	field.getTile().getLayer3(), 
									field.getPosX(), 
									field.getPosY(),
									null 						);
				}
			}
		}
	}
	
}//class
