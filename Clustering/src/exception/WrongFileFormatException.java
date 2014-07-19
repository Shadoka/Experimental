package exception;

import visitor.GuiExceptionVisitor;
import constants.ExceptionConstants;

public class WrongFileFormatException extends AbstractGuiException {

	private static final long serialVersionUID = 5883452016793138562L;

	public WrongFileFormatException(String format) {
		super(ExceptionConstants.WrongFileFormatMessage + format);
	}

	@Override
	public void accept(GuiExceptionVisitor v) {
		v.handle(this);
	}

}
