package de.thomas.creatures.implementation.controller;

import de.thomas.creatures.implementation.model.Creature;
import de.thomas.creatures.implementation.model.WorldModel;
import de.thomas.creatures.implementation.view.WorldView;

public class WorldController {
	private WorldModel worldModel;
	
	public WorldController(WorldModel worldModel) {
		this.worldModel = worldModel;
	}

	public void addCreature(Creature creature) {
		worldModel.addCreature(creature);
		creature.getAi().setWorldModel(worldModel);
		creature.getAi().init();
	}

	public void changeSpeed(double speedChange) {
		double change = WorldModel.speedFactor + speedChange;
		setSpeed(change);
	}

	private void setSpeed(double speed) {
		if (speed >= 0 && speed <= 15) {
			WorldModel.speedFactor = speed;
		}
	}
}
