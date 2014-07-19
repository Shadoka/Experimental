package util;

import java.util.Vector;

import common.Clusterable;

public class ClusterableCollection<X extends Clusterable> {

	private final Vector<X> points;

	public ClusterableCollection() {
		this.points = new Vector<X>();
	}
	
	public ClusterableCollection(Vector<X> points) {
		this.points = points;
	}
	
	public void add(X point) {
		this.getPoints().add(point);
	}
	
	public Vector<X> getPoints() {
		return points;
	}
}
