package util;

import metric.EuclideanDistance;

public class PointPair {

	private final Point p1, p2;
	private final double distance;
	
	public PointPair(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
		this.distance = EuclideanDistance.getInstance().distanceTo(p1, p2);
	}

	public Point getP1() {
		return p1;
	}

	public Point getP2() {
		return p2;
	}

	public double getDistance() {
		return distance;
	}
}
