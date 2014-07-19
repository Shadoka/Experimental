package exception;

import constants.ExceptionConstants;

public class ClusterLimitTooHighException extends AbstractClusteringException {

	private static final long serialVersionUID = 8435243954551669828L;

	public ClusterLimitTooHighException() {
		super(ExceptionConstants.ClusterLimitTooHighMessage);
	}

}
