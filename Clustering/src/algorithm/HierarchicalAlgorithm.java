package algorithm;

import java.util.Collection;
import java.util.List;
import java.util.Vector;

import common.StopCondition;

import util.ClusterPair;
import util.EuclideanCluster;
import util.Point;
import metric.DistanceMeasure;

public class HierarchicalAlgorithm extends AbstractAlgorithm {

	private final StopCondition condition;
	
	public HierarchicalAlgorithm(List<Point> points, DistanceMeasure measure, StopCondition condition) {
		super(points, measure);
		this.condition = condition;
		this.initClusters(points);
	}
	
	private void initClusters(Collection<Point> points) {
		for (Point p : points) {
			EuclideanCluster current = new EuclideanCluster();
			current.add(p);
			this.getCluster().add(current);
		}
	}
	
	public void cluster(){
		boolean continueClustering = true;
		while (continueClustering) {
			if (this.getCluster().size() == 1) break;
			Vector<ClusterPair> pairs = new Vector<ClusterPair>();
			for (int i = 0; i < this.getCluster().size()-1; i++) {
				EuclideanCluster c1 = this.getCluster().get(i);
				for (int x = i + 1; x < this.getCluster().size(); x++) {
					EuclideanCluster c2 = this.getCluster().get(x);
					pairs.add(new ClusterPair(c1, c2, this.getMeasure().distanceTo(c1.getCentroid(), c2.getCentroid())));
				}
			}
			ClusterPair bestMerger = this.selectBestPair(pairs);
			if (this.getCondition().continueClustering(this.getCluster(), bestMerger)) {
				this.getCluster().remove(bestMerger.getCluster1());
				this.getCluster().remove(bestMerger.getCluster2());
				this.getCluster().add(EuclideanCluster.merge(bestMerger.getCluster1(), bestMerger.getCluster2()));
			} else {
				continueClustering = false;
			}
		}
	}
	
	private ClusterPair selectBestPair(List<ClusterPair> pairs) {
		ClusterPair best = pairs.get(0);
		for (int i = 1; i < pairs.size(); i++) {
			if (pairs.get(i).getDistance() < best.getDistance()) best = pairs.get(i);
		}
		return best;
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

	public StopCondition getCondition() {
		return condition;
	}
}
