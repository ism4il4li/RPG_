package Items;

import java.awt.image.BufferedImage;
import java.io.File;

import Characters.Player;

/**
 * <p> Die Klasse Item beinhaltet alle nutzbaren Items, die verbraucht werden können und
 *     ist ein Bauplan für diese, die der Spieler nutzen kann. 
 * <p> Konstruktoren: 
 *
 * @param name String
 * @param price int
 * @param hp byte
 * @param ep byte
 * @param usableInBattle
 * 
 * @author dennisb/ismaila
 *
 */
public class Item {
		
		/**
		 * Name des Items : String
		 */
		private String name;
		/**
		 * Defaultpreis des Items abhängig von dem Shop : int
		 */
		private int price;
		/**
		 * hp Wert der beeinflusst wird wenn das item genutz wird : byte
		 */
		private byte hp;
		/**
		 * energie Wert der beeinflusst wird wenn das item genutzt wird : byte
		 */
		private byte ep;
		/**
		 * Gibt an ob das Item im Kampf benutzt werden kann : boolean
		 */
		private boolean usableInBattle;
		/**
		 * beeinflusst die Stärke des Spielers der es nutzt. 
		 */
		private byte strength;
		/**
		 * Enthaelt das icon des Items, welches auf dem Feld oder im Inventar angezeigt wird : BufferedImage
		 */
		private BufferedImage icon;
		/**
		 * zählt das vorkommen der Items : int
		 */
		private int count;
		/**
		 * Anzahl des gleichen Items im besitz der Creature : int
		 */
		private File soundFile;
		//effect koennte kommen
		/**
		 * @param name String
		 * @param price int
		 * @param hp byte
		 * @param ep byte
		 * @param usableInBattle boolean
		 * @param strength byte
		 * @param icon BufferedImage
		 */
		public Item(String name, int price, byte hp, byte ep, boolean usableInBattle, byte strength, BufferedImage icon, File soundFile) {
			
			this.name 			= 	name;
			this.price 			= 	price;
			this.hp 			= 	hp;
			this.ep 			= 	ep;
			this.usableInBattle = 	usableInBattle;
			this.strength 		=	strength;
			this.icon			=	icon;
			this.count			=	1;
			this.soundFile		=	soundFile;
			
		}
		/**
		 * Gibt den names des Itemobjektes zurück : String
		 * @return name : String
		 */
		public String getName() {
			return name;
		}
		/**
		 * Gibt den Preis //Wert// des Item zurück : int
		 * @return preis : int
		 */
		public int getPrice() {
			return price;
		}
		/**
		 * Gibt den EigenHP Wert des Items, um diesen wert wird dann geheilt, bzw geschädigt : byte
		 * @return hp : byte
		 */
		public byte getHp() {
			return hp;
		}
		/**
		 * Gibt den EigenEP Wert des Items, um diesen wert wird dann geheilt, bzw geschädigt : byte
		 * @return ep : byte
		 */
		public byte getEp() {
			return ep;
		}
		/**
		 * Prüft ob ein item im Kampf nutzbar ist : boolean
		 * @return boolean
		 */
		public boolean isUsableInBattle() {
			return usableInBattle;
		}
		/**
		 * Gibt die Images des Items zurück : BufferdImage
		 * @return img : BufferdImage
		 */
		public BufferedImage getIcon() {
			return this.icon;
		}
		/**
		 * Zählt wie oft das Item vorhanden ist : int
		 * @return int
		 */
		public int getCount() {
			return this.count;
		}
		/**
		 * erhöht die Anzahl des gleichen Items, welches die Creature trägt
		 */
		public void increaseCount() {
			this.count++;
		}
		/**
		 * verringert die Anzahl des gleichen Items, welches die Creature trägt
		 */
		public void decreaseCount() {
			this.count--;
		}
		/**
		 * Gibt das SoundFIle zurück, welches bei der Nutzung des Items zurückgegeben wird : File
		 * @return File
		 */
		public File getSound() {
			return this.soundFile;
		}
		/**
		 * Führt den Effekt eines Items aus, bisher nur beim Player...
		 * @param player
		 */
		public void executeItemEffect(Player player) 
		{
			switch(this.getName()) 
			{
				case "Heiltrank klein":
					player.increaseHP(this.hp);
					break;
				case "Heiltrank mittel":
					player.increaseHP(this.hp);
					break;
				case "Heiltrank gross":
					player.increaseHP(this.hp);
					break;
				case "Energietrank klein":
					player.increaseEP(this.ep);
					break;
			}
		}
		//Die Methode könnte ich gut gebrauchen
		@Override 
		public boolean equals(Object obj) {
			//Ist das obj == dieses Objekt
			if( obj == this)
				return true;
			//Wenn das obj ein Item ist dann
			if( obj instanceof Item ) 
			{
				//wir benutzen die Methode getName aus der Klasse Item, darum Casten wir das Objekt
				if((((Item) (obj)).getName()).equals(this.getName()))
					return true;
			}
			//Falls nichts davon zutrifft, ist es nicht das Gleiche für Uns.
			return false;
		}	
	
}
