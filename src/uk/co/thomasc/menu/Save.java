package uk.co.thomasc.menu;

import uk.co.thomasc.Main;
import uk.co.thomasc.Screen;

public class Save extends Screen {

	public Save() {
		if (Main.getGamestate() != null) {
			Main.getGamestate().save();
		}
	}
	
	@Override
	public void draw() {
		new MenuScreen();
	}

	@Override
	public void init() {
		
	}
	
}