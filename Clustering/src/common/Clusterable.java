package common;

import exception.AbstractClusteringException;
import util.Point;
import visitor.ClusterableVisitor;
import visitor.ClusterableVisitorReturn;

public interface Clusterable {

	public void accept(ClusterableVisitor v);
	public <X> X accept(ClusterableVisitorReturn<X> v);
	
	public Point getPoint() throws AbstractClusteringException;
}
