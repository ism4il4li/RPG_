import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
/**
* <p> Die Klasse Music dient als Initialisierung für sound Clips die während der Laufzeit genutzt werden udn verwaltet diese
* <p>arbeitet ohne Objekterzeugung
* 
* @author dennisb/ismaila
*/
public class Music {
	
	/**
	 * Dient als variable zum dynamischen auswählen von sounds, die direkt in Objekten anderer Klassen enthalten sind
	 */
	private static Clip clip;
	//Clipsdeklarationen
	private static Clip se_openMenu;
	private static Clip sound_Field;
	private static Clip sound_BattleEasy;
	private static Clip sound_GameMenu;
	
	/**
	 * Initialisiert die Sounds und macht diese fürs Spiel direkt nutzbar
	 */
	public static final void initSounds() {

		try 
		{
			se_openMenu			=	AudioSystem.getClip();
			sound_Field			=	AudioSystem.getClip();
			sound_BattleEasy	=	AudioSystem.getClip();
			sound_GameMenu		=	AudioSystem.getClip();
			
			se_openMenu.open(AudioSystem.getAudioInputStream(Helper.makeFile("res/openMenu.mid")));
			sound_Field.open(AudioSystem.getAudioInputStream(Helper.makeFile("res/field1.mid")));
			sound_BattleEasy.open(AudioSystem.getAudioInputStream(Helper.makeFile("res/battleEasy.mid")));
			sound_GameMenu.open(AudioSystem.getAudioInputStream(Helper.makeFile("res/menu.mid")));
		}
		catch(Exception e)
		{
			System.out.println("Fehler beim Laden der SoundDateien");
		}
	}
	/**
	 * Gibt den soundClip zurück für das öffnen des Inventars : Clip
	 * @return Clip
	 */
	public static final Clip getSe_openMenu() {
		return se_openMenu;
	}
	/**
	 * Gibt den sound zurück für das Level, hier fürs 1 level : Clip
	 * @return Clip
	 */
	public static final Clip getSound_Field() {
		return sound_Field;
	}
	/**
	 * Gibt den sound zurück für ein battle, leicht : Clip
	 * @return Clip
	 */
	public static final Clip getSound_BattleEasy() {
		return sound_BattleEasy;
	}
	/**
	 * Gibt den sound zurück für das Titelmenu : Clip
	 * @return
	 */
	public static final Clip getSound_GameMenu() {
		return sound_GameMenu;
	}
	/**
	 * Instanziert einen clip und startet diesen, geeignet um sounds die In Objekten anderer Klassen enthalten sind, abzuspielen : File
	 * @param sound : File
	 */
	public static final void playSoundMenu(File sound) {
		
		try {
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sound));
			clip.start();
		} catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
			System.out.println("Sound konnte nicht abgespielt werden");
		}
		
		
	}

	
}
