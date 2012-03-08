package uk.co.thomasc.entity;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import uk.co.thomasc.Main;
import uk.co.thomasc.Screen;
import uk.co.thomasc.menu.Game;

public class Trap extends Entity {
	
	public boolean sprung = false;
	
	public Trap() {
		randomisePosition();
	}

	@Override
	public void draw(Screen screen) {
		if (sprung || ((Game) screen).trainingMode) {
			screen.drawTexture(192, 32, 32, 32, Main.padding + getX() * Main.tileSize, Main.padding + getY() * Main.tileSize, 64, 64);
		}
	}

	@Override
	public void save(BufferedWriter out) throws IOException {
		super.save(out);
		out.write((sprung ? 1 : 0) + "\n");
	}
	
	@Override
	public void restore(List<String> in) {
		super.restore(in);
		sprung = in.remove(0).equals("1");
	}
}