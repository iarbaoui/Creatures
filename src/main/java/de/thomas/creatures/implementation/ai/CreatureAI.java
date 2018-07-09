package de.thomas.creatures.implementation.ai;

import de.thomas.creatures.implementation.model.Creature;
import de.thomas.creatures.implementation.model.Food;
import de.thomas.creatures.implementation.model.WorldModel;

import java.awt.*;

public abstract class CreatureAI {
	protected WorldModel worldModel;
	
	public abstract void init();
	public abstract Point.Double update(Point.Double currentTarget, Point.Double position, Food nearFood, Point.Double nearMatePosition, double energy, double maxEnergy);
	
	/*public Creature getCreature() {
		return creature;
	}*/
	
	/*public void setCreature(Creature creature) {
		this.creature = creature;
	}*/
	
	public WorldModel getWorldModel() {
		return worldModel;
	}
	
	public void setWorldModel(WorldModel model) {
		this.worldModel = model;
	}
}
