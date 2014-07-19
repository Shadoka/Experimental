package metric;

import common.Clusterable;
import exception.AbstractClusteringException;

import util.Point;

public class EuclideanDistance implements DistanceMeasure {

	private static EuclideanDistance instance = null;
	
	private EuclideanDistance() {
		
	}
	
	public static EuclideanDistance getInstance() {
		if (instance == null) instance = new EuclideanDistance();
		return instance;
	}
	
	@Override
	public double distanceTo(Clusterable p1, Clusterable p2) {
		Point point1 = null;
		Point point2 = null;
		try {
			point1 = p1.getPoint();
			point2 = p2.getPoint();
		} catch (AbstractClusteringException e) {
			e.printStackTrace();
		}
		double dx = point1.getX() - point2.getX();
		double dy = point1.getY() - point2.getY();
		return dx * dx + dy * dy;
	}
	

}
