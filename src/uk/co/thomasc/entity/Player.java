package uk.co.thomasc.entity;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import uk.co.thomasc.Main;
import uk.co.thomasc.Screen;

public class Player extends Entity {
	private int sprite;
	
	@Override
	public void move(int x, int y) {
		super.move(x, y);
		if (x < 0) {
			setSprite(1);
		} else if (x > 0) {
			setSprite(2);
		} else if (y < 0) {
			setSprite(3);
		} else if (y > 0) {
			setSprite(0);
		}
	}
	
	public void setSprite(int sprite) {
		this.sprite = sprite;
	}

	@Override
	public void draw(Screen screen) {
		screen.drawTexture(sprite * 32, 32, 32, 32, Main.padding + getX() * Main.tileSize, Main.padding + getY() * Main.tileSize, 64, 64);
	}
	
	@Override
	public void save(BufferedWriter out) throws IOException {
		super.save(out);
		out.write(sprite + "\n");
	}
	
	@Override
	public void restore(List<String> in) {
		super.restore(in);
		sprite = Integer.parseInt(in.remove(0));
	}
}