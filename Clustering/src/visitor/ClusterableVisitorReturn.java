package visitor;

import util.Point;

public interface ClusterableVisitorReturn<X> {

	public X handle(Point p);
}
