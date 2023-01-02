package Items;

/**
 * Bauplan für die Rüstung von dem  Spieler getragen wird
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
	 * Name der Rüstung : String
	 */
	private String name;
	/**
	 *  Defaultpreis der Rüstung abhängig von dem Shop : int
	 */
	private int price;
	/**
	 * Level ab dem der Spieler die Rüstung benutzen kann : byte
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
