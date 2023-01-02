package Items;
/**
 * 
 * <p> Die Klasse Weapon ist ein Bauplan für Waffen die der Spieler nutzen kann. 
 * <p> Konstruktoren: 
 *
 * @param name String
 * @param price int
 * @param level byte
 * @param damageMultiplicator float
 * 
 * @author dennisb
 *
 */
public class Weapon {

	/**
	 * Name der Waffe : String
	 */
	private String name;
	/**
	 * Defaultpreis der Waffe abhängig von dem Shop : int
	 */
	private int price;
	/**
	 * Level ab dem der Spieler die Waffe benutzen kann : byte
	 */
	private byte level;
	/**
	 * Der schaden einer Attacke wird mit dem DamageMultiplikator dieser Waffe multipliziert : float
	 */
	private float weaponMultiplicator;
	//effect koennte kommen
	/**
	 * 
	 * @param name String
	 * @param price int
	 * @param level byte
	 * @param damageMultiplicator float
	 */
	public Weapon(String name, int price, byte level, float weaponMultiplicator) {
		
		this.name = name;
		this.price = price;
		this.level = level;
		this.weaponMultiplicator = weaponMultiplicator;
		
	}
	
}
