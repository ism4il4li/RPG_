import Characters.Player;
import Items.Item;

public class Game {
	
	public static void main(String[] args) {
		runGame();
	}
	
	private static void runGame() {
		
		Images.initImages();
		Item[] items = Items.initItems();
		Music.initSounds();
		Music.getSound_GameMenu().start();
		Player player = new Player("Ismail", (byte)100, (byte)100, (short)200,(short)200, null, null, (byte)10);
		player.setCharacterSprites(Images.getCharacterSet((short)1));
		Level level1 		= new Level( 1, 260, 260);
		Screen screen 		= new Screen("Title", 656, 676);
		Leinwand panel 		= new Leinwand(level1, player, items);
		Movement control 	= new Movement(panel);
		panel.setDoubleBuffered(true);
		
		//Nur für die präsentation
		player.addItem(items[0]);
		player.addItem(items[1]);
		player.addItem(items[2]);
		player.addItem(items[3]);
		player.decreaseHP((byte)70);
		player.decreaseEP((byte)30); 
		//
		screen.add(panel);
		screen.addKeyListener(control);
		
		boolean loop = true;
		
		while(loop) 
		{
			System.out.println(player.getPosX());
			Helper.sleep(12);
			panel.repaint();
			if(panel.isGameOver)
				loop = false;
		}
		screen.dispose();
	}
	
}
