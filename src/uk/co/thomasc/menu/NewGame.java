package uk.co.thomasc.menu;

import uk.co.thomasc.GameState;
import uk.co.thomasc.Main;
import uk.co.thomasc.RenderPriority;
import uk.co.thomasc.entity.Flask;
import uk.co.thomasc.entity.Trap;

public class NewGame extends Game {

	private int trapCount;
	
	public NewGame() {
		super();
		
		trapCount = 2;
		
		Main.setGamestate(new GameState());
		Main.getGamestate().registerForRender(RenderPriority.Low, Main.getGamestate().getPlayer());
		Main.getGamestate().registerForRender(RenderPriority.Normal, Main.getGamestate().getMonster());
		newGrid();
	}
	
	private void newGrid() {
		trapCount = Math.min((Main.xLen * Main.yLen) - 3, trapCount);
		
		Main.getGamestate().getMonster().randomisePosition();
		
		Main.getGamestate().registerForRender(RenderPriority.High, new Flask());
		for (int i = 0; i < trapCount; i++) {
			Main.getGamestate().registerForRender(RenderPriority.High, new Trap());
		}
	}
	
	@Override
	public void draw() {
		super.draw();
	}

	@Override
	public void init() {
		super.init();
	}
	
}