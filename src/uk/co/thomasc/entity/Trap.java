package uk.co.thomasc.entity;

import java.util.List;

public class Trap extends Entity {
	
	public Trap(List<Entity> entities) {
		randomisePosition(entities);
	}
	
}