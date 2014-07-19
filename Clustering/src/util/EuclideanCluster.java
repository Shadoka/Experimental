package util;

import java.util.Iterator;
import java.util.Vector;

import metric.EuclideanDistance;

public class EuclideanCluster implements AbstractCluster {

	private Point centroid;
	private double diameter;
	private double radius;
	private Vector<Point> points;
	
	public EuclideanCluster() {
		this.points = new Vector<>();
	}
	
	public EuclideanCluster(Vector<Point> points) {
		this.points = points;
		this.recalculateAll();
	}

	public Point getCentroid() {
		return centroid;
	}

	public void setCentroid(Point centroid) {
		this.centroid = centroid;
	}

	public void add(Point point) {
		this.getPoints().add(point);
		this.calculateNewCentroid();
		this.calculateNewDiameter();
		this.calculateNewRadius();
	}
	
	private void recalculateAll() {
		this.calculateNewCentroid();
		this.calculateNewDiameter();
		this.calculateNewRadius();
	}
	
	private void calculateNewDiameter() {
		double maxDistance = 0;
		for (int i = 0; i < this.getPoints().size()-1; i++) {
			Point p1 = this.getPoints().get(i);
			for (int x = i + 1; x < this.getPoints().size(); x++) {
				 double currentDistance = EuclideanDistance.getInstance().distanceTo(p1, this.getPoints().get(x));
				 if (currentDistance > maxDistance) maxDistance = currentDistance;
			}
		}
		this.setDiameter(maxDistance);
	}
	
	private void calculateNewRadius() {
		double maxDistance = 0;
		for (Point p : this.getPoints()) {
			double currentDistance = EuclideanDistance.getInstance().distanceTo(this.getCentroid(), p);
			if (currentDistance > maxDistance) maxDistance = currentDistance;
		}
		this.setRadius(maxDistance);
	}
	
	private void calculateNewCentroid() {
		double sumX = 0, sumY = 0;
		int count = 0;
		for (Point p : this.getPoints()) {
			sumX += p.getX();
			sumY += p.getY();
			count++;
		}
		this.setCentroid(new Point(sumX/count, sumY/count));
	}
	
	public double getDensity(boolean withRadius) {
		if (withRadius) {
			double realRadius = this.getRealRadius();
			return this.getPoints().size() / (realRadius * realRadius);
		} else {
			double realDiameter = this.getRealDiameter();
			return this.getPoints().size() / (realDiameter * realDiameter);
		}
	}
	
	public static EuclideanCluster merge(EuclideanCluster first, EuclideanCluster second) {
		Vector<Point> newPoints = new Vector<>();
		for (Point p : first.getPoints()) {
			newPoints.add(p);
		}
		for (Point p : second.getPoints()) {
			newPoints.add(p);
		}
		EuclideanCluster result = new EuclideanCluster(newPoints);
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof EuclideanCluster) {
			EuclideanCluster other = (EuclideanCluster) o;
			boolean result = true;
			for (Point p : this.getPoints()) {
				result = result && other.getPoints().contains(p);
			}
			return result;
		}
		return false;
	};
	
	@Override
	public String toString() {
//		String result = "[";
//		Iterator<Point> i = this.getPoints().iterator();
//		while (i.hasNext()) {
//			Point p = i.next();
//			result += "( " + p.getX() + ", " + p.getY() + ")";
//			if (i.hasNext()) result += ", ";
//		}
//		result += "], Centroid: (" + this.getCentroid().getX() + ", " + this.getCentroid().getY() + "), Diameter: " + this.getRealDiameter() + ", Radius: " + this.getRealRadius() + ", Density: " + this.getDensity(true);
		return "Cluster";
	}

	public Vector<Point> getPoints() {
		return points;
	}

	public void setPoints(Vector<Point> points) {
		this.points = points;
		this.recalculateAll();
	}

	public double getDiameter() {
		return diameter;
	}
	
	public double getRealDiameter() {
		return Math.sqrt(this.diameter);
	}

	public void setDiameter(double diameter) {
		this.diameter = diameter;
	}

	public double getRadius() {
		return radius;
	}
	
	public double getRealRadius() {
		return Math.sqrt(this.radius);
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	
}
