package observer;

import java.util.Vector;

import util.EuclideanCluster;

public class ClusterChangedEvent implements Event {

	private final Vector<EuclideanCluster> newCluster;

	public ClusterChangedEvent(Vector<EuclideanCluster> newCluster) {
		this.newCluster = newCluster;
	}
	
	public Vector<EuclideanCluster> getNewCluster() {
		return newCluster;
	}

	@Override
	public void accept(EventVisitor v) {
		v.handle(this);
	}
}
