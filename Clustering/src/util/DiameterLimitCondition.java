package util;

import java.util.Vector;

import common.StopCondition;

public class DiameterLimitCondition implements StopCondition {

	private final double diameterLimit;
	
	public DiameterLimitCondition(double diameterLimit) {
		this.diameterLimit = diameterLimit * diameterLimit;
	}
	
	@Override
	public boolean continueClustering(Vector<EuclideanCluster> cluster,
			ClusterPair bestMerger) {
		EuclideanCluster temp = EuclideanCluster.merge(bestMerger.getCluster1(), bestMerger.getCluster2());
		return temp.getDiameter() <= this.getDiameterLimit();
	}

	public double getDiameterLimit() {
		return diameterLimit;
	}

}
