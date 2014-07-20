package algorithm;

import java.util.List;
import java.util.Vector;

import observer.Observee;

import metric.DistanceMeasure;

import util.EuclideanCluster;
import util.Point;

public abstract class AbstractAlgorithm extends Observee implements Algorithm {

	private List<Point> points;
	private DistanceMeasure measure;
	private Vector<EuclideanCluster> cluster;
	
	public AbstractAlgorithm(List<Point> points, DistanceMeasure measure) {
		this.points = points;
		this.measure = measure;
		this.cluster = new Vector<>();
	}
	
	public List<Point> getPoints() {
		return points;
	}
	public void setPoints(Vector<Point> points) {
		this.points = points;
	}
	public Vector<EuclideanCluster> getCluster() {
		return cluster;
	}
	public void setCluster(Vector<EuclideanCluster> cluster) {
		this.cluster = cluster;
	}
	public DistanceMeasure getMeasure() {
		return measure;
	}
	public void setMeasure(DistanceMeasure measure) {
		this.measure = measure;
	}
}
