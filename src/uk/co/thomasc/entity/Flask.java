package uk.co.thomasc.entity;

import java.util.List;

public class Flask extends Entity {
	
	public Flask(List<Entity> entities) {
		randomisePosition(entities);
	}
	
}