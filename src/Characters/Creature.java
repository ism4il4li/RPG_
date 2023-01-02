package Characters;
import Items.Armor;
import Items.Weapon;
//Für Ismail: Abstrakte Klasse ist die Superklasse von Player und Enemy. Hier enthalte sind die Gemeinsamkeiten der 
//Kindsklassen
/**
* <p> Die Abstrakte Klasse Creature dient als Bauplan für die Player Klasse und die Enemy Klasse. 
* <p> Konstruktoren: 
*
* @param name String
* @param price int
* @param level byte
* @param damageMultiplicator float
* 
* @author dennisb/ismaila
*/

public abstract class Creature {
	/**
	 * Name des Characters : String
	 */
	protected String name;//Spielername des Objektes
	/**
	 * Lebenspunkte, Energiepunkte und Stärke des Characters default : byte , byte, byte
	 */
	protected byte hp, ep, strength;
	/**
	 * Position
	 */
	protected short x, y;
	/**
	 * Aktuelle Rüstung die vom Character getragen wird : Armor
	 */
	protected Armor armor;
	/**
	 * Aktuelle Waffe die vom Character genutzt wird : Weapon
	 */
	protected Weapon weapon;
	
	protected float speed;
	/**
	 * Erzeugt ein Objekt vom Typ creature. Klassen die hiervon erben sind: Player und Enemy
	 * @param name 	 : String
	 * @param hp 	 : byte
	 * @param ep 	 : byte
	 * @param x 	 : short ?
	 * @param y 	 : short ?
	 * @param armor  : Armor
	 * @param weapon : Weapon
	 */
	public Creature(String name, byte hp, byte ep, byte strength, short x, short y, Armor armor, Weapon weapon ) {
		
		this.name 		= 	name;
		this.hp 		= 	hp;
		this.ep 		= 	ep;
		this.strength 	= 	strength;
		this.x 			= 	x;
		this.y 			= 	y;
		this.armor 		= 	armor;
		this.weapon 	= 	weapon;
		this.speed		=	1;
		
	}
	/**
	 * gibt die aktuellen Lebenspunkte des Objektes zurück : byte
	 * @return
	 */
	public byte getHP() {
		return this.hp;
	}
	/**
	 * setzt die Aktuellen hp des Objektes
	 * @param 
	 * 
	 */
	public byte setHP(byte maxHP) {
		return this.hp;
	}
	/**
	 * gibt die Aktuellen ep des Objektes zurück
	 * @return
	 * byte
	 */
	public byte getEP() {
		return this.ep;
	}
	/**
	 * Setzt die aktuelle waagerechte Position des Objektes auf der Map
	 * @param 
	 */
	public void setPosX(short xPos) {
		this.x = xPos;
	}
	/**
	 * gibt die aktuelle waagerechte Position des Objektes auf der Map als short zurueck
	 * @return
	 * short posX
	 */
	public short getPosX() {
		return this.x;
	}
	/**
	 * Setzt die aktuelle waagerechte Position des Objektes auf der Map
	 * @param 
	 */
	public void setPosY(short yPos) {
		this.y = yPos;
	}
	/**
	 * gibt die aktuelle senkrechte Position des Objektes auf der Map als short zurueck unten 0 bis oben mapSize
	 * @return
	 * short posY
	 */
	public short getPosY() {
		return this.y;
	}
	/**
	 * Gibt den namen des Characters zurück : String
	 * @return String
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * Gibt die Geschwindigkeit zurück mit welcher sich ein Character auf der map bewegt zurück : float
	 * @return float speed
	 */
	public float getSpeed() {
		return this.speed;
	}
	/**
	 * bewegt den Character nach Links
	 * @param speed : float 
	 */
	public void moveLeft(float speed) {
		if(this.x > 0) {
			this.x -= (int)(3 * speed);
		}
	}
	/**
	 * bewegt den Character nach Rechts
	 * @param speed : float 
	 */
	public void moveRight(float speed) {
		this.x += (int)(3 * speed);
	}
	/**
	 * bewegt den Character nach Oben
	 * @param speed : float 
	 */
	public void moveUp(float speed) {
		this.y -= (int)(3 * speed);
	}
	/**
	 * bewegt den Character nach Unten
	 * @param speed : float 
	 */
	public void moveDown(float speed) {
		if( this.y > 0)
			this.y += (int)(3 * speed);
	}
	
}//class
