import java.awt.Image;
import java.awt.image.BufferedImage;
/**
* <p> Die Klasse Images dient als Initialisierung für die BufferedImages die während der Laufzeit genutzt werden und um diese an die 
* <p> entsprechenden Objekte weiterzugeben
* arbeitet ohne Objekterzeugung
* 
* @author dennisb/ismaila
*/
public class Images {
	/**
	 * Image für das Mainmenu : BufferdImage
	 */
	private static BufferedImage menu;
	//Zeilen und Spalten des 2d tilesetArrays in welches die images für die Tiles eingelesen werden
	private static final int 	X								= 	8;
	private static final int 	Y								= 	30;
	//Tilesets als 2d Array
	private static final BufferedImage tileset_FOREST	[][] 	= 	new BufferedImage[X][Y];
	private static final BufferedImage tileset_CITY		[][]	= 	new BufferedImage[X][Y];
	//Images für Items
	private static BufferedImage item_HEILTRANK;
	//SpielerImages
	private static BufferedImage PLAYERIMAGE;
	private static final BufferedImage PLAYERSPRITES	[][]	= 	new BufferedImage[4][4];
	//
	//ENEMYSPRITES
	//
	private static BufferedImage ENEMYIMAGE;
	//
	//BATTLEBACKS
	//
	private static BufferedImage BATTLEBACK_GRASS;
	
	/**
	 * Initialisiert die Bilder fürs Spiel
	 */
	public static void initImages() {
		
		menu = Helper.readImage("res/menu.png");
		
		PLAYERIMAGE				=	Helper.readImage("res/playerImage.png");
		
		item_HEILTRANK			=	Helper.readImage("res/heiltrank.png");
		//Enemys
		ENEMYIMAGE				=	Helper.readImage("res/ENEMY.png");
		
		//Battlebacks
		BATTLEBACK_GRASS		=	Helper.readImage("res/battleGrass.jpg");
		
		BufferedImage forest 	= 	Helper.readImage("res/forest.png");
		//tilesetCity	  = Helper.readImage(tilesetCity, "res/city");
		
			for( int x = 0; x < X; x++) 
			{
				for(int y = 0; y < Y; y++) 
				{
					tileset_FOREST[x][y] = forest.getSubimage(x * Tile.WIDTH, y * Tile.HEIGTH, Tile.WIDTH, Tile.HEIGTH);
					//imgTileSet_City[x][y] = (Image)(img.getSubimage(x * Tile.WIDTH, y * Tile.HEIGTH, Tile.WIDTH, Tile.HEIGTH));
				}
			}
			
			initPlayerImages();
		}
	
	/**
	 * Initialisiert die Playersprites
	 */
	private static final void initPlayerImages()
	{
		BufferedImage img = Helper.readImage("res/PlayerSprite.png");
		
		for( int x = 0; x < PLAYERSPRITES.length; x++) 
		{
			for(int y = 0; y < PLAYERSPRITES[x].length; y++) 
			{
				Images.PLAYERSPRITES[x][y] = img.getSubimage(x * 32, y * 48, 32, 48);
			}
		}
	}
	/**
	 * 
	 * <p>Gibt Tileset mit entsprechender (id : short) zurück : BufferdImage[][]
	 * <p>1 = Forest
	 * <p>2 = City
	 * </li>
	 * @param id
	 * @return BufferdImage[][]
	 */
	public static final BufferedImage[][] getTileset(short id){
		
		switch(id) 
		{
			case 1:
				return tileset_FOREST;
			case 2:
				return tileset_CITY;
			default:
				return tileset_FOREST;
		}
	}
	/**
	 * 
	 * <p>Gibt SpriteSet eines Characters mit (id : short) zurück : BufferdImage[][]
	 * <p>1 = Player
	 * <p>2 = Player...
	 * </li>
	 * @param id
	 * @return BufferdImage[][]
	 */
	public static final BufferedImage[][] getCharacterSet(short id){
		
		switch(id) 
		{
			case 1:
				return Images.PLAYERSPRITES;
			case 2:
				return Images.PLAYERSPRITES;
			default:
				return Images.PLAYERSPRITES;
		}
	}
	/**
	 * Gibt den Hintergrund für das Spielmenue zurück: BufferedImage
	 * @return
	 */
	public static final BufferedImage getMenu() {
		return Images.menu;
	}
	/**
	 * Gibt das Bild für alle Heiltränke zurück : BufferedImage
	 * @return BufferedImage
	 */
	public static final BufferedImage getItemHeiltrank() {
		return item_HEILTRANK;
	}
	/**
	 * Gibt das Bild des Spielers zurück für zb. Für Menus, den Kampf, Textnachrichten
	 * @return BufferedImage
	 */
	public static final BufferedImage getPlayerImage() {
		return PLAYERIMAGE;
	}
	/**
	 * Gibt einen Battlebackground zurück welcher mit dem Parameter i gewählt werden kann : BufferdImage
	 * @param i
	 * @return BufferdImage
	 */
	public static final BufferedImage getBattleBackground(int i) {
		
		switch(i) 
		{
			case 1:
				return BATTLEBACK_GRASS;
			default:
				return BATTLEBACK_GRASS;
		}
	}

	/**
	 * Gibt die Grafik des Gegners im Battle zurück. Kann entsprechend mit einer id gewäehlt werden : BufferedImage
	 * @param i
	 * @return BufferedImage
	 */
	public static final Image getEnemyImage(int i) {
		switch(i) 
		{
			case 1:
				return ENEMYIMAGE;
			default:
				return ENEMYIMAGE;
		}
	}
	
}//class
