package exception;

import constants.ExceptionConstants;
import metric.DistanceMeasure;

public class WrongDistanceMeasureException extends AbstractClusteringException {

	private static final long serialVersionUID = -7921906859497143016L;

	public WrongDistanceMeasureException(DistanceMeasure measure) {
		super(ExceptionConstants.WrongDistanceMeasureMessage + measure + "!");
	}

}
