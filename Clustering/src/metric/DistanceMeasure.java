package metric;

import common.Clusterable;
import exception.WrongDistanceMeasureException;

public interface DistanceMeasure {

	/**
	 * Returns the distance between the given Clusterable <code>p1</code> and <code>p2</code>.<br>
	 * <b>ATTENTION</b> If the Clusterables are in an euclidean space, the squared distance will be returned
	 * due to performance advantages.
	 * @param p1 : Clusterable.
	 * @param p2 : Clusterable.
	 * @return : distance as double.
	 * @throws WrongDistanceMeasureException : If concrete Clusterable and distance measure are incompatible.
	 */
	public double distanceTo(Clusterable p1, Clusterable p2);
}
