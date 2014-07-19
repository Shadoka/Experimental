package algorithm;

import java.util.Iterator;
import java.util.Random;
import java.util.Vector;

import metric.DistanceMeasure;
import metric.EuclideanDistance;

import util.AmountLimitCondition;
import util.EuclideanCluster;
import util.Point;
import util.PointToMultiplePointsDistances;

public class KMeansAlgorithm extends AbstractAlgorithm {

	private final double samplePercentage = 0.4;
	private final int k;
	
	public KMeansAlgorithm(Vector<Point> points, DistanceMeasure measure, boolean preClustering, int k) {
		super(points, measure);
		this.k = k;
		this.initialize(preClustering);
	}
	
	/**
	 * Initializes the starting clusters.<br>
	 * Depending on the <code>preClustering</code> flag this happens either via hierachical preclustering
	 * or via random start choice with following furthest-point-method.
	 * @param preClustering : boolean.
	 */
	private void initialize(boolean preClustering) {
		if (preClustering) {
			this.performHierarchicalPreclustering();
		} else {
			this.initRandom();
		}
	}
	
	/**
	 * Clusters a random sample of the data hierarchical until k clusters are created.
	 * k is given via user-input and the sample percentage is momentarily set to 0.4 of the total
	 * data.
	 */
	private void performHierarchicalPreclustering() {
		int pointsNeeded = (int) (this.getPoints().size() * this.samplePercentage);
		Vector<Point> preClusterPoints = new Vector<>();
		Random rnd = new Random();
		while (preClusterPoints.size() < pointsNeeded) {
			int nextRnd = rnd.nextInt(this.getPoints().size());
			preClusterPoints.add(this.getPoints().get(nextRnd));
			this.getPoints().remove(nextRnd);
		}
		HierarchicalAlgorithm hierAlgo = new HierarchicalAlgorithm(preClusterPoints, EuclideanDistance.getInstance(), new AmountLimitCondition(this.k));
		hierAlgo.cluster();
		this.setCluster(hierAlgo.getCluster());
	}
	
	/**
	 * This method chooses k points, which will act as starting clusters.
	 * The first point is chosen randomly, while the following points are selected via 
	 * furthest-point-method. Read a book for explaining.
	 */
	private void initRandom() {
		Random rnd = new Random();
		int startNumber = rnd.nextInt(this.getPoints().size());
		Vector<PointToMultiplePointsDistances> pairs;
		Vector<Point> startClusters = new Vector<>();
		startClusters.add(this.getPoints().get(startNumber));
		this.getPoints().remove(startNumber);
		while (startClusters.size() < this.k) {
			pairs = new Vector<>();
			for (Point p : this.getPoints()) {
				PointToMultiplePointsDistances current = new PointToMultiplePointsDistances(p);
				for (Point startPoint : startClusters) {
					current.add(startPoint);
				}
				pairs.add(current);
			}
			PointToMultiplePointsDistances best = this.getBestPair(pairs);
			startClusters.add(best.getRelevantPoint());
			this.getPoints().remove(best.getRelevantPoint());
		}
		for (Point p : startClusters) {
			EuclideanCluster cluster = new EuclideanCluster();
			cluster.add(p);
			this.getCluster().add(cluster);
		}
	}
	
	/**
	 * Returns the best candidate for having as a starting cluster.
	 * It selects the point, which maximizes the minimum distance to any other already selected 
	 * starting cluster.
	 * @param pairs : Vector.
	 * @return sth.
	 */
	private PointToMultiplePointsDistances getBestPair(Vector<PointToMultiplePointsDistances> pairs) {
		if (pairs.size() > 1) {
			PointToMultiplePointsDistances result = pairs.get(0);
			for (int i = 1; i < pairs.size(); i++) {
				PointToMultiplePointsDistances current = pairs.get(i);
				if (current.getMinDistance() > result.getMinDistance()) result = current;
			}
			return result;
		} else {
			return pairs.get(0);
		}
	}
	
	/**
	 * This method assigns each point its corresponding nearest cluster.
	 * After this assignment it will be deleted from the list of to-be-clustered points.
	 */
	public void cluster() {
		Iterator<Point> i = this.getPoints().iterator();
		while (i.hasNext()) {
			Point current = i.next();
			if (this.getCluster().size() > 1) {
				EuclideanCluster nearestCluster = this.getCluster().get(0);
				double nearestClusterDistance = this.getMeasure().distanceTo(current, nearestCluster.getCentroid());
				for (int x = 1; x < this.getCluster().size(); x++) {
					double currentDistance = this.getMeasure().distanceTo(current, this.getCluster().get(x).getCentroid());
					if (currentDistance < nearestClusterDistance) {
						nearestCluster = this.getCluster().get(x);
						nearestClusterDistance = currentDistance;
					}
				}
				nearestCluster.add(current);
			} else {
				this.getCluster().get(0).add(current);
			}
			i.remove();
		}
	}

	@Override
	public String toString() {
		String result = "";
		int i = 1;
		for (EuclideanCluster c : this.getCluster()) {
			result += i + ". Cluster: " + c + "\n";
			i++;
		}
		return result;
	}
}
