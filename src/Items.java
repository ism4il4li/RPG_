import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import Items.Item;
/**
* <p> Die Klasse Items dient als Initialisierung für die Items die wäährend der Laufzeit genutzt werden können und an
* <p> die entsprechenden Objekte weitergegeben werden können
* arbeitet ohne Objekterzeugung
* 
* @author dennisb/ismaila
*/
public class Items {
	/**
	 * Initialisiert die Items fürs Spiel. Diese werden aus der .txt items eingelesen und als objekte erzeugt und zurückgegeben
	 * @return Item[]
	 */
	public static final Item[] initItems() {
		
		RandomAccessFile rf; 
		try 
		{
			String temp;
			rf 		= new RandomAccessFile(Helper.makeFile("res/items.txt"), "r");
			int j   = Helper.intValueOf(rf.readLine());
			int i   = 0;
			Item[] items = new Item[j];
			
			while( ( temp = rf.readLine()) != null )
			{
				String[] s 	= 	temp.split(",");
				File sound  =   Helper.makeFile(s[7]);
				items[i] 	=	new Item(							 s[0]				,
											Helper.intValueOf		(s[1])				, 
											(byte)(Helper.intValueOf(s[2]))				,
											(byte)(Helper.intValueOf(s[3]))				,
											s[4].equalsIgnoreCase("true") ? true : false,
											(byte)(Helper.intValueOf(s[5]))				,
											Helper.readImage		(s[6])				,
											sound										);
				i++;
			}
			return items;
		} 
		catch (IOException e) 
		{
			System.out.println("File konnte nicht gelesen werden!");
		}
		return null;
	}	
	
}
