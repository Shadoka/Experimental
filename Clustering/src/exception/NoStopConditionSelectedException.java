package exception;

import visitor.GuiExceptionVisitor;
import constants.ExceptionConstants;

public class NoStopConditionSelectedException extends AbstractGuiException {

	private static final long serialVersionUID = -1559643825008923105L;

	public NoStopConditionSelectedException() {
		super(ExceptionConstants.NoConditionSelectedMessage);
	}

	@Override
	public void accept(GuiExceptionVisitor v) {
		v.handle(this);
	}

}
