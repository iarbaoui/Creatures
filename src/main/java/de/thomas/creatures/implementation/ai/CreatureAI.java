package de.thomas.creatures.implementation.ai;

import de.thomas.creatures.implementation.model.Food;
import de.thomas.creatures.implementation.model.WorldModel;

import java.awt.*;

public abstract class CreatureAI {
	public abstract void init(double worldWidth, double worldHeight);
	public abstract Point.Double update(Point.Double currentTarget, Point.Double position, Food nearFood, Point.Double nearMatePosition, double energy, double maxEnergy);
}
