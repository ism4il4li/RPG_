
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
public class backuplevel {
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
	public backuplevel(int level, int startX, int startY ) {
		
		//Einlesen der Layer und zuweisung durch die fields an die Tiles
		File mapLayer1, mapLayer2, passableTiles;
		RandomAccessFile rfLayer1, rfLayer2, rfPassable;
		int cntFieldX;
		int cntFieldY;
		
		switch(level) 
		{
			case 1:
				mapLayer1 		= new File("res/level1.txt");
				mapLayer2 		= new File("res/level1_1.txt");
				passableTiles 	= new File("res/pass_tileset1.txt");
				
				break;
			case 2:
				mapLayer1 		= new File("res/level1.txt");
				mapLayer2 		= new File("res/level1_1.txt");
				passableTiles 	= new File("res/pass_tileset1.txt");
				break;
			default:
				mapLayer1 = new File("res/level1.txt");
				mapLayer2 = new File("res/level1_1.txt");
				passableTiles 	= new File("res/pass_tileset1.txt");
				break;
		}
		
		try 
		{
			boolean[][] temp = new boolean[8][30];
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
			
			
			rfLayer1 			= 	new RandomAccessFile(mapLayer1,"r");
			String tempL1 		= 	rfLayer1.readLine();
			String[] tempL1A	= 	tempL1.split(" ");
			cntFieldX 			= 	Helper.intValueOf(tempL1A[0]);
			cntFieldY 			= 	Helper.intValueOf(tempL1A[1]);
			int tilesetLayer1 	= 	Helper.intValueOf(tempL1A[2]);
			this.fields 		= 	new Field[cntFieldX][cntFieldY];	
			int j = 0;
			String tempL2;
			while( (tempL1 = rfLayer1.readLine()) != null ) 
			{
				String[] tempL1B = tempL1.split(" ");
				for(int i = 0; i < tempL1B.length; i++)
				{	
					this.fields[i][j] = new Field( 	i * Tile.WIDTH, 
													j * Tile.HEIGTH, 
													new Tile(tempL1B[i] ,tilesetLayer1, temp ) );
				}
				j++;
			}
			rfLayer1.close();
			
			//
			//layer 2 setzen
			//
			rfLayer2 			= 	new RandomAccessFile( mapLayer2, "r");
			j = 0;
			while( (tempL2 = rfLayer2.readLine()) != null ) {
				String[] tempL2B = tempL2.split(" ");
				for(int i = 0; i < tempL2B.length; i++)
				{	
					this.fields[i][j].getTile().setLayer2(tempL2B[i], tilesetLayer1, temp);
				}
				j++;
			}
			rfLayer2.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		this.startX = 300;
		this.startY = 300;	
	}
	
	/**
	 * Gibt die Felder des Levels zurück
	 * @return
	 */
	public Field[][] getFields() {
		return this.fields;
	}
	
}

