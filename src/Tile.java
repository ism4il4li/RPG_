import java.awt.image.BufferedImage;
/**
* <p> Die Klasse Tile dient als Attribut von SPielfeldern, in denen 3 Ebenen zum zeichnen enthalten sind, sowie die 
* <p> Gr�sse eines Feldes und der in dem Tile enthaltenen BufferedImages, sowie die Passierbarkeit von Feldern
* <p> Konstruktoren: // ben�tigt die Eintr�ge aus der .txt in welcher die maps enthalten sind sowie headerinformationen
* 
* @param mapEntries : String
* @param tilesetLayer1 : int
* @param passables : boolean[][]
* 
* @author dennisb/ismaila
*/
public class Tile {
	//Ebenen zum zeichnen
	private BufferedImage layer1;
	private BufferedImage layer2;
	private BufferedImage layer3;
	//switches zum pr�fen der passierbarkeit f�r den Player...sp�ter auch Enemys und Friends
	private boolean isPassableLayer1;
	private boolean isPassableLayer2;
	/**
	 * weite eines TIles und somit eines Spielfeldes
	 */
	protected static final int WIDTH = 32;
	protected static final int HEIGTH = 32;
	/**
	 * Ben�tigt informationen aus den Mapfiles bzw. aus dem TilesetFiles, die mit dem wort pass_ beginnen
	 * @param mapEntries
	 * @param tilesetLayer1
	 * @param passables
	 */
	public Tile(String mapEntries, int tilesetLayer1, boolean[][] passables ) {
		
		String[] mapEntry 			= 	mapEntries.split("/");
		BufferedImage[][] tileset 	= 	Images.getTileset((short) tilesetLayer1);
		
		if(tileset != null) 
		{
			switch(tilesetLayer1) 
			{	
			   case 1:
					this.layer1 			= 	tileset[Helper.intValueOf(mapEntry[0])][Helper.intValueOf(mapEntry[1])];
					this.isPassableLayer1 	= 	passables[Helper.intValueOf(mapEntry[0])][Helper.intValueOf(mapEntry[1])];
					break;
			   case 2:
				    this.layer1 			= 	tileset[Helper.intValueOf(mapEntry[0])][Helper.intValueOf(mapEntry[1])];
				    break;
			}
		}
	}
	/**
	 * gibt die erste ebenen eines Tiles zur�ck//untergrund// : BufferdImage
	 * @return BufferdImage
	 */
	public BufferedImage getLayer1() {
		return this.layer1;
	}
	/**
	 * gibt die zweite ebene eines Tiles zur�ck : BufferdImage
	 * @return BufferdImage
	 */
	public BufferedImage getLayer2() {
		return this.layer2;
	}
	/**
	 * gibt die dritte ebene eines Tiles zur�ck//bisher nur f�r events genutzt// : BufferdImage
	 * @return BufferdImage
	 */
	public BufferedImage getLayer3() {
		return this.layer3;
	}
	/**
	 * Ben�tigt informationen aus den Mapfiles bzw. aus dem TilesetFiles, die mit dem wort pass_ beginnen
	 * @param mapEntries
	 * @param tilesetLayer2
	 * @param passables
	 */
	public void setLayer2(String mapEntries, int tilesetLayer2, boolean[][] passables) {
		
		String[] mapEntry = mapEntries.split("/");
		BufferedImage tileset[][] = Images.getTileset((short)tilesetLayer2);
		if(tileset != null) 
		{
				switch(tilesetLayer2) 
				{	
				   case 1:
						this.layer2 = tileset[Helper.intValueOf(mapEntry[0])][Helper.intValueOf(mapEntry[1])];
						this.isPassableLayer2 = passables[Helper.intValueOf(mapEntry[0])][Helper.intValueOf(mapEntry[1])];
						break;
				   case 2:
					    this.layer2 = tileset[Helper.intValueOf(mapEntry[0])][Helper.intValueOf(mapEntry[1])];
					    break;
				}
		}
	}
	/**
	 * Ben�tigt BufferdImage von einem Objekt was auf layer3 gezeichnet werden soll
	 * @param img : BufferedImage
	 */
	public void setLayer3(BufferedImage img) {
		this.layer3 = img;
	}
	/**
	 * Setzt die Passierbarkeit eines Tiles f�r den Spieler...sp�ter mehr
	 * @param passable : boolean
	 */
	public void setPassableLayer1(boolean passable) {
		this.isPassableLayer1 = passable;
	}
	/**
	 * pr�ft ob ein Tile passierbar ist : boolean
	 * @return boolean
	 */
	public boolean getPassable() {
		return ( this.isPassableLayer1 && this.isPassableLayer2);
	}
	/**
	 * Setzt die Passierbarkeit eines Tiles f�r den Spieler...sp�ter mehr
	 * @param passable
	 */
	public void setPassableLayer2(boolean passable) {
		this.isPassableLayer2 = passable;
	}
	

}
