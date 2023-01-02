package Items;

/**
 * Bauplan f�r die R�stung von dem  Spieler getragen wird
 * <p> Konstuktoren :
 * @param name String
 * @param price int 
 * @param level byte
 * @param armorMultiplicator float
 * 
 * @author ismaila/dennisb
 *
 */
public class Armor {
	/**
	 * Name der R�stung : String
	 */
	private String name;
	/**
	 *  Defaultpreis der R�stung abh�ngig von dem Shop : int
	 */
	private int price;
	/**
	 * Level ab dem der Spieler die R�stung benutzen kann : byte
	 */
	private byte level;
	/**
	 * Der schaden einer Attacke wird mit dem DamageMultiplikator dieser Waffe multipliziert : float
	 */
	private float armorMultiplicator;
	/**
	 * @param name  : String
	 * @param price : int 
	 * @param level : byte
	 * @param armorMultiplicator : float
	 */
	public Armor (String name, int price , byte level, float armorMultiplicator) {
		this.name 	= 	name;
		this.price 	= 	price;
		this.level 	= 	level;
		this.armorMultiplicator = armorMultiplicator;
	}

}
