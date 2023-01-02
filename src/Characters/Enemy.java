package Characters;

import java.util.ArrayList;

import Items.Armor;
import Items.Item;
import Items.Weapon;
/**
 * Enemy ist noch eine Baustelle noch, kommt noch...
 * 
 * <p>Konstruktoren: 1
 * @param name 	 : String
 * @param hp   	 : byte
 * @param ep 	 : byte
 * @param x 	 : short
 * @param y 	 : short
 * @param armor  : Armor
 * @param weapon : Weapon
 * @param level  : byte
 *
 * 
 * @author Dennis/ismail
 *
 */
public class Enemy extends Creature {
/**
 * Hoechstgrenze für die HP der Enemys : final byte = 200
 */
	protected final byte maxHP;
/**
 * Gegner Level : byte
 */
	protected byte level;
/**
 * Item das ein Enemy halten und nutzen kann : byte
 */
	protected Item item;
/**
 * @param name 	: String
 * @param hp   	: byte
 * @param ep 	: byte
 * @param x 	: short
 * @param y 	: short
 * @param armor : Armor
 * @param weapon: Weapon
 * @param level : byte
 */
	
public Enemy(String name, byte hp, byte ep, short x, short y, Armor armor, Weapon weapon, byte level, Item item, byte strength) {
	super(name, hp, ep, strength, x, y, armor, weapon);
	this.maxHP 	= 	(byte) 200;
	this.level 	= 	level;
	this.item  	=	item;
}
/**
 * Erhoeht die HP des Objektes um den übergebenden Wert, jedoch hoechstens bis maxHP
 * @param value
 * byte
 */
public void increaseHP(byte value) {
		
		if(this.getHP() + value > maxHP)
		{
			this.setHP(maxHP);
		} 
		else
		{
			this.setHP(value);
		}
	}
/**
 * Verringert die HP des Objektes um den übergebenden Wert, jedoch mindestens 0
 * @param value
 * byte
 */
	public void decreaseHP(byte value) {
		
		byte hp = this.getHP();
		
		if( hp - value < 0)
		{
			this.setHP((byte)0);
		} 
		else
		{
			this.setHP( (byte)(hp - value) );
		}
	}
	//
	//
	//wurde bisher nicht benötigt
	//
	//
	//TODO
	@Override
	public void setPosX(short xPos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public short getPosX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPosY(short yPos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public short getPosY() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
