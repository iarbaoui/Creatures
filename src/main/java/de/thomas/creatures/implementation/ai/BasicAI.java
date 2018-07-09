package de.thomas.creatures.implementation.ai;

import java.awt.Point;
import java.util.Vector;

public class BasicAI extends CreatureAI {
	private Vector<Point.Double> wayPoints;
	private final int WAY_POINT_NUMBER = 10;

	public BasicAI() {
		wayPoints = new Vector<Point.Double>();
	}

	@Override
	public void init(double worldWidth, double worldHeight) {
		initWayPoints(worldWidth, worldHeight);
	}

	private void initWayPoints(double worldWidth, double worldHeight) {
		int wayPointNumberX = WAY_POINT_NUMBER;
		int wayPointNumberY = (int) (WAY_POINT_NUMBER * ((double) worldHeight / (double) worldWidth));

		double deviationX = worldWidth / wayPointNumberX;
		double deviationY = worldHeight / wayPointNumberY;

		double maxRandom = deviationX / 2;

		for (int y = 0; y < wayPointNumberY; y++) {
			for (int x = 0; x < wayPointNumberX; x++) {
				Point.Double point = new Point.Double(x * deviationX + Math.random() * maxRandom,
						y * deviationY + Math.random() * maxRandom);

				wayPoints.add(point);
			}
		}
	}

	@Override
	public Point.Double update(Point.Double currentTarget, Point.Double position, Point.Double nearFoodPosition, double nearFoodValue, Point.Double nearMatePosition, double energy, double maxEnergy) {
		Point.Double target = currentTarget;
		//Move random
		if (currentTarget == null) {
			Point.Double point = wayPoints.get((int) (Math.random() * wayPoints.size()));
			target = point;
		}
		//Goto food
		if (nearFoodPosition != null && energy + nearFoodValue <= maxEnergy) {
			target = nearFoodPosition;
		}
		//Goto mate
		if (nearMatePosition != null) {
			target = nearMatePosition;
		}
		return target;
	}

	public Vector<Point.Double> getWayPoints() {
		return wayPoints;
	}
}
