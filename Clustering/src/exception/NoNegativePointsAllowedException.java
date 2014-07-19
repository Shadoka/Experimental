package exception;

import constants.ExceptionConstants;
import util.Point;

public class NoNegativePointsAllowedException extends AbstractClusteringException {

	private static final long serialVersionUID = -5618330176314313353L;
	
	public NoNegativePointsAllowedException(String message, Point point) {
		super(ExceptionConstants.NoNegativePointsAllowedMessage + point + "!");
	}

}
