package util;

public class ClusterPair {

	private final EuclideanCluster cluster1;
	private final EuclideanCluster cluster2;
	private final double distance;
	
	public ClusterPair(EuclideanCluster cluster1, EuclideanCluster cluster2, double distance) {
		this.cluster1 = cluster1;
		this.cluster2 = cluster2;
		this.distance = distance;
	}

	public double getDistance() {
		return distance;
	}

	public EuclideanCluster getCluster1() {
		return cluster1;
	}

	public EuclideanCluster getCluster2() {
		return cluster2;
	}
}
