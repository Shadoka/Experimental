package util;

import java.util.Vector;

import common.StopCondition;

public class RadiusLimitCondition implements StopCondition {

	private final double radiusLimit;
	
	public RadiusLimitCondition(double radiusLimit) {
		this.radiusLimit = radiusLimit * radiusLimit;
	}

	@Override
	public boolean continueClustering(Vector<EuclideanCluster> cluster,
			ClusterPair bestMerger) {
		EuclideanCluster temp = EuclideanCluster.merge(bestMerger.getCluster1(), bestMerger.getCluster2());
		return temp.getRadius() <= this.getRadiusLimit();
	}
	
	public double getRadiusLimit() {
		return radiusLimit;
	}
}
