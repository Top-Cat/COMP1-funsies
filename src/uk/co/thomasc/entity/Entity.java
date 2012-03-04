package uk.co.thomasc.entity;

import java.util.List;
import java.util.Random;

import uk.co.thomasc.Main;
import uk.co.thomasc.Screen;

public abstract class Entity {
	
	private int x;
	private int y;
	private Random random = new Random();
	
	public void move(int x, int y) {
		this.x = Math.min(Math.max(0, this.x + x), Main.xLen - 1);
		this.y = Math.min(Math.max(0, this.y + y), Main.yLen - 1);
	}
	
	public void randomisePosition(List<Entity> entities) {
		x = 0;
		y = 0;
		boolean found = true;
		while (found) {
			found = false;
			x = random.nextInt(Main.xLen);
			y = random.nextInt(Main.yLen);
			for (Entity entity : entities) {
				if (!entity.equals(this) && entity.getX() == x && entity.getY() == y) {
					found = true;
				}
			}
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}

	public abstract void draw(Screen screen);
	
}
