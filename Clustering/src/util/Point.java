package util;

import visitor.ClusterableVisitor;
import visitor.ClusterableVisitorReturn;
import common.Clusterable;
import exception.AbstractClusteringException;

public class Point implements Clusterable {

	private double x, y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "[" + this.x + ", " + this.y + "]";
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Point) {
			Point other = (Point) o;
			return this.getX() == other.getX() && this.getY() == other.getY();
		}
		return false;
	}
	
	@Override
	public void accept(ClusterableVisitor v) {
		v.handle(this);
	}
	
	@Override
	public <X> X accept(ClusterableVisitorReturn<X> v) {
		return v.handle(this);
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	@Override
	public Point getPoint() throws AbstractClusteringException {
		return this;
	}
}
