package Characters;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import Items.Armor;
import Items.Item;
import Items.Weapon;
/**
 * Die Player Klasse ist ein Bauplan zur Instanzierung der Hauptfigur. Hier sind f�r die Spiellogik, bez�glich des Spielers, alle relevanten 
 * informationen enthalten. Erbt von Creature
 *
 * <p>Konstruktoren  : 1
 * @param name 	     : String
 * @param hp      	 : byte
 * @param ep 	 	 : byte
 * @param x 	 	 : short
 * @param y 	 	 : short
 * @param armor  	 : Armor
 * @param weapon     : Weapon
 * @param maxInvetorySize : byte
 * 
 * <p>zus�tzlich hat der player noch ein inventory ohne eintraege : ArrayList,item
 * @author Dennis/ismail
 */
public class Player extends Creature {
/**
 * Aktuelle hoechstgrenze fuer die SpielerHP default 100 :byte
 */
	protected byte maxHP;
/**
 * Aktuelle Erfahrungspunkte des Spielers default 0 : int
 */
	protected int exp;
/**
 * Rucksack des Spielers in dem die Items liegen : Arraylist von dem Datentyp Item
 */
	protected ArrayList<Item> inventory;
/**
 * Die maximale Anzahl an Items, die der Spieler tragen kann : byte
 */
	protected byte maxInventorySize;
/**
 * Aktuelles Spieler-Level default 1 : byte
 */
	protected BufferedImage[][] playerSprites;
	
	protected byte level;
/**
 * Variable die zur Pr�fung der aktuellen Richtung des Players : byte
 * 0 = sued
 * 1 = west
 * 2 = ost
 * 3 = nord
 */
	protected byte direction;
/**
 * Attribut zum Pr�fen und Zeichen von animierten Sprites : byte	
 */
	protected byte animStep;
/**
 * Attribut zum bestimmen, nach wie vielen durchl�ufen in der GameLoop, der Spieler gezeichnet werden soll um die animationen zu regulieren
 */
	protected byte animRender;
/**
 * Attribut welches die Maximalen Energiepunkte des Spielers angibt
 */
	protected byte maxEP;
	
/**
 * @param name 	 : String
 * @param hp     : byte
 * @param ep 	 : byte
 * @param x 	 : short
 * @param y		 : short
 * @param armor  : Aromr
 * @param weapon : Weapon
 * @param maxInvetorySize : byte
 */
	
public Player(String name, byte hp, byte ep, short x, short y, Armor armor, Weapon weapon, byte strength) {
	super(name, hp, ep, strength, x, y, armor, weapon);
	this.maxHP 				= 	100;
	this.maxEP				=	100;
	this.exp 				= 	0;
	this.maxInventorySize 	= 	20;
	this.level	 			= 	1;
	this.inventory 			= 	new ArrayList<>();
	this.speed 				= 	1;
	this.direction 			= 	0;
	this.animStep 			= 	0;
	this.animRender			=	0;
}

/**
 * Erhoeht die HP des Objektes um den �bergebenden Wert, jedoch hoechstens bis maxHP
 * @param value
 * byte
 */
public void increaseHP(byte value) {
		
		if(this.hp + value > maxHP)
		{
			this.hp = maxHP;
		} 
		else
		{
			this.hp += value;
		}
	}
/**
 * Verringert die HP des Objektes um den �bergebenden Wert, jedoch mindestens 0
 * @param value
 * byte
 */
	public void decreaseHP(byte value) {
		
		if( this.hp - value < 0)
		{
			this.hp = ((byte)0);
		} 
		else
		{
			this.hp = (byte)(hp - value);
		}
	}

/**
 * Verringert die HP des Objektes um den �bergebenden Wert, jedoch mindestens 0
 * @param value
 * byte
 */
		public void decreaseEP(byte value) {
			
			if( this.ep - value < 0)
			{
				this.ep = ((byte)0);
			} 
			else
			{
				this.ep = (byte)(ep - value);
			}
		}
/**
* Erhoeht die EP des Objektes um den �bergebenden Wert, jedoch hoechstens bis maxEP
* @param value
* byte
*/
	public void increaseEP(byte value) {
			
			if(this.ep + value > maxEP)
			{
				this.ep = maxEP;
			} 
			else
			{
				this.ep += value;
			}
		}	

/**
* F�gt dem Objekt ein Item in seinem Inventar hinzu
* 
* @param item : Item
*/
	public void addItem(Item item) {
		
		if(this.inventory.size() < this.maxInventorySize) {
			if(this.inventory.size() < 1) 
			{
				this.inventory.add(item);
			}
			else 
			{
				for(Item i : this.inventory) 
				{
					if( i.getName().equals(item.getName())) 
					{
						i.increaseCount();
						break;
					}
					else 
					{
						this.inventory.add(item);
						break;
					}	
				}
			}
		}
	}

/**
* Entfernt das �bergebene Item aus dem Inventar des Spielers falls vorhanden
* 
* @param item : Item
*/
	public void removeItem(Item item) {
		for( Item i : this.inventory) {
			if( i != null ) {
				if( i.equals(item) ) 
				{
					if(i.getCount() == 1) 
					{
						this.inventory.remove(i);
						break;
					}
					else 
					{
						i.decreaseCount();
					}
				}
			} 
		}	
	}
/**
* Pr�ft ob ein Spieler ein bestimmtes Item in seinem Inventar hat, f�r events, Levelaufstieg... : boolean
* 
* @param item : Item
* @return boolean
*/	
	public boolean hasItem(Item item) {
		for( Item i : this.inventory) {
			if(i.equals(item))
				return true;
		}
		return false;
	}
/**
 * Gibt die aktuelle Maximalen HealthPoints des Spielers zur�ck
 * @return byte
 */
	public byte getMaxHP() {
		return this.maxHP;
	}
/**
 * Gibt die aktuelle Maximalen EnergiePoints des Spielers zur�ck
 * @return byte
 */	
	public byte getMaxEP() {
		return this.maxEP;
	}
/**
 * setzt die Sprites//subimages//f�r den Spieler, ben�tigt BufferedImage[][]	
 * @param img : BufferedImage
 */
	public void setCharacterSprites(BufferedImage[][] img) {
		this.playerSprites = img;
	}
/**
 * Gibt die aktuellen CharacterSprites des Spielers zur�ck : BufferedImage[][]
 * @return playerSprites : BufferdImages[][]
 */
	public BufferedImage[][] getCharacterSprites(){
		return this.playerSprites;
	}
/**
 * Methode zum animieren des Players, indem verschieden subimages in den playersprites gew�hlt werden k�nnen
 * regelt zudem die schnelligkeit der wiedergabe	
 */
	public void makeAnimStep() {
		
		if( this.animRender == 4) 
		{
			this.animRender = 0;
			if(this.animStep < 3) 
			{
				this.animStep++;
			}
			else
			{
				this.animStep = 0;
			}	
		}
		this.animRender++;
	}
/**
 * Gibt den aktuellen animationStep des Players zur�ck//welche Reihe des Sprites// : byte
 * @return byte
 */
	public byte getAnimStep() {
		return this.animStep;
	}
/**
 * Gibt die richtung des Spielers zur�ck, u.a. nutzbar zum animieren des Players : byte	
 * @return
 */
	public byte getDirection() 
	{
		return this.direction;
	}
/**
 * setzt die Richtung des Spielers, ben�tigt richtung als byte()	
 * 0 = sued
 * 1 = west
 * 2 = ost
 * 3 = nord
 */
	public void setDirection(byte direction) {
		this.direction = direction;
	}
/**
 * Gibt ein Item aus dem inventar des Players zur�ck, falls keines vorhanden null : Item	
 * @param itemName String
 * @return item Item
 */
	public Item getItem(String itemName){
		for( Item x : inventory ) {
			if(x.getName().equalsIgnoreCase(itemName))
				return x;
		}
		return null;
	}
/**
 * Gibt die anzahl der Elemente im Inventar zur�ck//mehrfachvorkommen werden nicht ber�cksichtigt// : int	
 * @return int elements
 */
	public int getInventorySize() {
		return this.inventory.size();
	}
/**
 * Gibt die anzahl der Elemente im Inventar zur�ck, die der Spieler maximal tragen kann//mehrfachvorkommen werden nicht ber�cksichtigt// : int	
 * @return
 */
	public int getMaxInventorySize() {
		return this.maxInventorySize;
	}
/**
 * Gibt das Inventar als liste zur�ck, noch nicht fertig..nur �bergangsl�sung zum pr�sentieren der Software
 * @return
 */
	//TODO
	public ArrayList<Item> getInventory() {
		return this.inventory;
	}
	
}//class
