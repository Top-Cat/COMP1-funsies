package uk.co.thomasc.entity;

import java.util.List;

import uk.co.thomasc.Screen;

public class Trap extends Entity {
	
	public boolean sprung = false;
	
	public Trap(List<Entity> entities) {
		randomisePosition(entities);
	}

	@Override
	public void draw(Screen screen) {
		//Traps are invisible
	}
	
}