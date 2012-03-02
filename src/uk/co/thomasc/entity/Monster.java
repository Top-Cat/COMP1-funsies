package uk.co.thomasc.entity;

import uk.co.thomasc.menu.Game;

public class Monster extends Entity {
	public boolean awake;
	private int cycles = 0;
	
	public void move(Player player) {
		if (cycles++ > 3) {
			cycles = 0;
			randomisePosition(Game.getInstance().entities);
			awake = false;
		} else {
			moveP();
			moveP();
		}
	}
	
	private void moveP() {
		Player player = Game.getInstance().player;
		if (getY() < player.getY()) {
			move(0, 1);
		} else if (getY() > player.getY()) {
			move(0, -1);
		} else if (getX() < player.getX()) {
			move(1, 0);
		} else if (getX() > player.getX()) {
			move(-1, 0);
		}
		if (getX() == player.getX() && getY() == player.getY()) {
			System.out.println("nom nom nom");
		}
	}
}