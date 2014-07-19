package exception;

import constants.ExceptionConstants;

public class MixedEuclideanAndNonEuclideanPointsException extends AbstractClusteringException {

	private static final long serialVersionUID = -2250152246272054696L;

	public MixedEuclideanAndNonEuclideanPointsException() {
		super(ExceptionConstants.MixedEuclideanAndNonEuclideanPointsMessage);
	}

}
