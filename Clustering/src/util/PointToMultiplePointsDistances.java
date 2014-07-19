package util;

import java.util.Vector;

import metric.EuclideanDistance;

public class PointToMultiplePointsDistances {

	private final Point relevantPoint;
	private Vector<PointDistanceWrapper> otherPoints;
	
	public PointToMultiplePointsDistances(Point point) {
		this.relevantPoint = point;
		this.otherPoints = new Vector<>();
	}
	
	public void add(Point other) {
		this.getOtherPoints().add(new PointDistanceWrapper(other, EuclideanDistance.getInstance().distanceTo(this.getRelevantPoint(), other)));
	}
	
	public double getMinDistance() {
		if (this.getOtherPoints().size() > 1) {
			double result = this.getOtherPoints().get(0).getDistance();
			for (PointDistanceWrapper current : this.getOtherPoints()) {
				if (current.getDistance() < result) result = current.getDistance();
			}
			return result;
		} else {
			return this.getOtherPoints().get(0).getDistance();
		}
	}

	public Point getRelevantPoint() {
		return relevantPoint;
	}

	public Vector<PointDistanceWrapper> getOtherPoints() {
		return otherPoints;
	}

	public void setOtherPoints(Vector<PointDistanceWrapper> otherPoints) {
		this.otherPoints = otherPoints;
	}
}
