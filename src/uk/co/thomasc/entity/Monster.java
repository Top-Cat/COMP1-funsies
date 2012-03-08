package uk.co.thomasc.entity;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import uk.co.thomasc.Main;
import uk.co.thomasc.Screen;
import uk.co.thomasc.menu.Game;

public class Monster extends Entity {
	public boolean awake;
	//private int cycles = 0;
	
	public void move(Player player) {
		moveP();
		moveP();
	}
	
	private void moveP() {
		Player player = Main.getGamestate().getPlayer();
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
			Game.getInstance().toast.setData("Game Over!", 128, 0, 64);
			Game.getInstance().toast.show();
		}
	}

	@Override
	public void draw(Screen screen) {
		if (awake || ((Game) screen).trainingMode) {
			screen.drawTexture(128, 0, 64, 64, Main.padding + getX() * Main.tileSize, Main.padding + getY() * Main.tileSize, 64, 64);
		}
	}

	@Override
	public void save(BufferedWriter out) throws IOException {
		super.save(out);
		out.write((awake ? 1 : 0) + "\n");
	}
	
	@Override
	public void restore(List<String> in) {
		super.restore(in);
		awake = in.remove(0).equals("1");
	}
}