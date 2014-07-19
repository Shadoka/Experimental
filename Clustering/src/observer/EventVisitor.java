package observer;

public interface EventVisitor {

	public void handle(ClusterChangedEvent e);
	public void handle(NewPointsEvent e);
}
