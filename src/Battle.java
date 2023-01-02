import java.awt.image.BufferedImage;

import Characters.Enemy;
import Characters.Player;

public class Battle {

	private Player player;
	private Enemy enemy;
	private Enemy enemy1;
	private Enemy enemy2;
	private byte countEnemies;
	private byte difficulty;
	private boolean playerTurn;
	private BufferedImage battleBackground;
	
	public Battle(Player player, Enemy enemy) {
		
		this.player 			= 	player;
		this.enemy 				= 	enemy;
		this.countEnemies 		= 	1;
		this.difficulty			=	0;
		this.playerTurn			=	true;
		this.battleBackground	= 	Images.getBattleBackground(1);
	}
	
	public Battle(Player player, Enemy enemy, Enemy enemy1) {
		
		super();
		this.enemy1 		= 	enemy1;
		this.countEnemies 	=	2;
	}

	public Battle(Player player, Enemy enemy, Enemy enemy1, Enemy enemy2) {
		
		super();
		this.enemy2			=	enemy2;
		this.countEnemies 	= 	3;
		
	}
	
	public void start() {

		if(this.difficulty < 2)
			System.out.println("");
		
	}
	
	
}
