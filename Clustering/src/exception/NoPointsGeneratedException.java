package exception;

import visitor.GuiExceptionVisitor;
import constants.ExceptionConstants;

public class NoPointsGeneratedException extends AbstractGuiException {

	private static final long serialVersionUID = 4608501315859154531L;

	public NoPointsGeneratedException() {
		super(ExceptionConstants.NoPointsGeneratedMessage);
    }

	@Override
	public void accept(GuiExceptionVisitor v) {
		v.handle(this);
	}
}