import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
/**
* <p> Die Klasse Helper dient als Hilfsklasse, zum Ein- und Auslesen von Dateien und um einen Thread schlafen zu legen
* <p> Zusätzlich steuert sie String Umwandlung in den Datentyp Integer
* <p> Erweiterbar
* 
* 
* @author dennisb/ismaila
*/
public class Helper {

	/**
	 * Liefert den Integer Wert eines übergebenen strings, falls NumberFormatException -1
	 * @param s
	 * @return int
	 */
	public static final int intValueOf(String s) {
		
		try 
		{
			return Integer.valueOf(s);
		}
		catch(NumberFormatException e)
		{
			return -1;
		}
		
	}
	/**
	 * Liest ein Image aus einer Datei ein und gibt daraus ein BufferedImage zurück
	 * @param path : String
	 * @return BufferedImage
	 */
	public static final BufferedImage readImage(String path) {
		
		BufferedImage img;
		try 
		{
			img = ImageIO.read(new File(path));
			return img;
		} 
		catch (Exception e) 
		{
			System.err.println("Fehler beim lesen des Bildes oder Dateipfad Existiert nicht!");
			return null;
		}
	}
	/**
	 * Erzeugt ein neues File mit dem angegebenen Pfad und liefert dieses zurück, bei Exception null
	 * @param path : String
	 * @return File
	 */
	public static final File makeFile(String path) {
		
		try 
		{
			return new File(path);
		}
		catch(Exception e) 
		{
			System.out.println("File:" + path + "konnte nicht gefunden werden!");
			return null;
		}
		
	}
	/**
	 * Legt einen Thread mit übergebenen ms schlafen
	 * @param ms
	 */
	public static final void sleep(int ms) {
		
		try 
		{
			Thread.sleep(ms);
		} 
		catch (InterruptedException e) {
			System.out.println("Thread konnte nicht gestoppt werden!");
		}
	}
	
	
}