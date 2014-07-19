package common;

import java.util.Vector;

import util.ClusterPair;
import util.EuclideanCluster;

public interface StopCondition {

	/**
	 * Returns true, if the condition to abort further clustering is <b>not</b> met.
	 * @param cluster : Vector of EuclideanCluster.
	 * @param bestMerger : ClusterPair.
	 * @return : boolean.
	 */
	public boolean continueClustering(Vector<EuclideanCluster> cluster, ClusterPair bestMerger);
}
