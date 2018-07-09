package de.thomas.creatures.implementation.ai;

import de.thomas.creatures.implementation.model.Food;

import java.awt.*;

public class DoNothingAI extends CreatureAI {

	@Override
	public void init(double worldWidth, double worldHeight) {
	}

	@Override
	public Point.Double update(Point.Double currentTarget, Point.Double position, Food nearFood, Point.Double nearMatePosition, double energy, double maxEnergy) {
		return null;
	}
}
