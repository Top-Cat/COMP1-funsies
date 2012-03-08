package uk.co.thomasc.entity;

import uk.co.thomasc.Main;
import uk.co.thomasc.Screen;
import uk.co.thomasc.menu.Game;

public class Flask extends Entity {
	
	public Flask() {
		randomisePosition();
	}

	@Override
	public void draw(Screen screen) {
		if (((Game) screen).trainingMode) {
			screen.drawTexture(96, 0, 32, 32, Main.padding + getX() * Main.tileSize, Main.padding + getY() * Main.tileSize, 64, 64);
		}
	}
	
}