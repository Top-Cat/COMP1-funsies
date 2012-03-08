package uk.co.thomasc.menu;

import uk.co.thomasc.GameState;
import uk.co.thomasc.Main;
import uk.co.thomasc.Screen;

public class Load extends Screen {

	public Load() {
		Main.setGamestate(new GameState());
		Main.getGamestate().restore();
	}
	
	@Override
	public void draw() {
		new MenuScreen();
	}

	@Override
	public void init() {
		
	}
	
}