import java.io.File;
import java.io.RandomAccessFile;
/**
 * Die Klasse Level dient dem Aufbau eines Levels und trägt die unterschiedlichen Informationen zusammen um diese dann
 * <p>der Klasse Leinwand zu übergeben. Das Level besteht zum einen aus den Feldern, welche Positionsangaben enthalten sowie die Feldgrafiken
 * <p>Konstruktoren:
 * 
 * @param level : int //id des levels
 * @param startX : int// startposition auf welcher sich der Player befindet
 * @param startY : int// startposition auf welcher sich der Player befindet
 * 
 * 
 * @author dennisb/ismaila
 *
 */
public class Level {
	/**
	 * Alle Felder des Konstuierten levels
	 */
	private Field[][] fields;
	private int startX;
	private int startY;
	
	//auslesen der infos
	/**
	 * 
	 * @param level
	 * @param startX
	 * @param startY
	 */
	public Level(int level, int startX, int startY ) {
		
		//Einlesen der Layer und zuweisung durch die fields an die Tiles
		File mapLayer1, mapLayer2, passableTiles;
		RandomAccessFile rfLayer1 = null, rfLayer2 = null, rfPassable;
		int cntFieldX;
		int cntFieldY;
		this.startX = startX;
		this.startY = startY;	
		
		switch(level) 
		{
			case 1:
				mapLayer1 		= new File("res/level1.txt");
				mapLayer2 		= new File("res/level1_1.txt");
				passableTiles 	= new File("res/pass_tileset1.txt");
				
				break;
				
			case 2:
				mapLayer1 		= new File("res/level2.txt");
				mapLayer2 		= new File("res/level2_2.txt");
				passableTiles 	= new File("res/pass_tileset1.txt");
				
				break;
				
			case 3:
				mapLayer1 		= new File("res/level3.txt");
				mapLayer2 		= new File("res/level3_3.txt");
				passableTiles 	= new File("res/pass_tileset1.txt");
				
				break;
				
			default:
				mapLayer1 = new File("res/level1.txt");
				mapLayer2 = new File("res/level1_1.txt");
				passableTiles 	= new File("res/pass_tileset1.txt");
				break;
		}
		
		boolean[][] temp = new boolean[8][30];
		try 
		{
			rfPassable 		= 	new RandomAccessFile( passableTiles, "r");
			int spalte = 0;
			String test;
			while( (test = rfPassable.readLine()) != null && spalte < 8 ) {
				String[] tempL2B = test.split(" ");

				for(int i = 0; i < tempL2B.length; i++)
				{	
					if( Helper.intValueOf(tempL2B[i]) == 0) 
					{
						temp[spalte][i] = false;
					}
					else 
					{
						temp[spalte][i] = true;
					}
					
				}
				spalte++;
			}
			rfPassable.close();
			
			rfLayer1				=	new RandomAccessFile(mapLayer1, "r");
			rfLayer2				=	new RandomAccessFile(mapLayer2, "r");
			String[] tempHeaderL1 	= 	rfLayer1.readLine().split(" ");
			cntFieldX				=	Helper.intValueOf(tempHeaderL1[0]);
			cntFieldY				=	Helper.intValueOf(tempHeaderL1[1]);
			int tileset				=	Helper.intValueOf(tempHeaderL1[2]);
			this.fields 			= 	new Field[cntFieldX][cntFieldY];
			
			String lineL1 = "";
			String lineL2 = "";
			String[] lineL1Split = null;
			String[] lineL2Split = null;
			int y = 0;
			while( ((lineL1 = rfLayer1.readLine()) != null) )
			{
				lineL1Split = lineL1.split(" ");
				for(int x = 0; x < cntFieldX; x++) 
				{
					this.fields[x][y] = new Field( 	x * Tile.WIDTH, 
													y * Tile.HEIGTH, 
													new Tile(lineL1Split[x], tileset, temp ) );
				}
				y++;
			}
			
			rfLayer1.close();
			
			y = 0;
			while( (lineL2 = rfLayer2.readLine()) != null ) {
				lineL2Split = lineL2.split(" ");
				for(int x = 0; x < cntFieldX; x++) 
				{
					this.fields[x][y].getTile().setLayer2(lineL2Split[x], 1, temp);
				}
				y++;
			}
			
		}catch(Exception e) {}
			//
			//layer 2 setzen
			//
	}
	
	/**
	 * Gibt die Felder des Levels zurück
	 * @return
	 */
	public Field[][] getFields() {
		return this.fields;
	}
	
}
