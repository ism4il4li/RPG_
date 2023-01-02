import java.awt.Image;

/**
* <p> Die Klasse Field dient als Bauplan für die einzelnen Spielfelder innerhalb eines Levels 
* <p> Konstruktoren: 
*
* @param x //position// : int
* @param y //position// : int
* @param tile : Tile
* 
* @author dennisb/ismaila
*/
public class Field {
	/**
	 * Position des Feldes auf der Map in Pixeln
	 */
	private int x,y;
	/**
	 * Tile auf dem die Images und Layer enthalten sind, sowie die Passierbarkeit für den Player
	 */
	private Tile tile;
	/**
	 * 
	 * @param x :int
	 * @param y	:int
	 * @param tile	:Tile
	 */
	public Field(int x, int y, Tile tile ) {
		this.x = x;
		this.y = y;
		this.tile = tile;
	}
	/**
	 * Gibt die aktuelle Position x des Feldes in Pixeln
	 * @return int
	 */
	public int getPosX() {
		return this.x;
	}
	/**
	 * Gibt die aktuelle Position y des Feldes in Pixeln
	 * @return int
	 */
	public int getPosY() {
		return this.y;
	}
	/**
	 * Gibt das Tile des Feldes zurück 
	 * @return Tile
	 */
	public Tile getTile() {
		return this.tile;
	}
	
	
}
