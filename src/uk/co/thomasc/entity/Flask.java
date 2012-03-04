package uk.co.thomasc.entity;

import java.util.List;

import uk.co.thomasc.Screen;

public class Flask extends Entity {
	
	public Flask(List<Entity> entities) {
		randomisePosition(entities);
	}

	@Override
	public void draw(Screen screen) {
		//The flask is invisible
	}
	
}