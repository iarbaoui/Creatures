package de.thomas.creatures.implementation.ai;

import java.awt.*;

public abstract class CreatureAI {
	public abstract void init(double worldWidth, double worldHeight);
	public abstract Point.Double update(Point.Double currentTarget, Point.Double position, Point.Double nearFoodPosition, double nearFoodValue, Point.Double nearMatePosition, double energy, double maxEnergy);
}
