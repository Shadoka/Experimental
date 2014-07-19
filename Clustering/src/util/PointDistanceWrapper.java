package util;

public class PointDistanceWrapper {

	private final Point p;
	private final double distance;
	
	public PointDistanceWrapper(Point p, double distance) {
		this.p = p;
		this.distance = distance;
	}

	public Point getP() {
		return p;
	}

	public double getDistance() {
		return distance;
	}
}
