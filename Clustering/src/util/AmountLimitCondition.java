package util;

import java.util.Vector;

import common.StopCondition;

public class AmountLimitCondition implements StopCondition {

	private final int maxAmount;
	
	public AmountLimitCondition(int maxAmount) {
		this.maxAmount = maxAmount;
	}
	
	@Override
	public boolean continueClustering(Vector<EuclideanCluster> cluster, ClusterPair bestMerger) {
		return cluster.size() > this.maxAmount;
	}

	public int getMaxAmount() {
		return maxAmount;
	}

}
