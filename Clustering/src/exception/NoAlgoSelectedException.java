package exception;

import constants.ExceptionConstants;
import visitor.GuiExceptionVisitor;

public class NoAlgoSelectedException extends AbstractGuiException {

	private static final long serialVersionUID = 5559556326657232502L;

	public NoAlgoSelectedException() {
		super(ExceptionConstants.NoAlgoSelectedMessage);
	}

	@Override
	public void accept(GuiExceptionVisitor v) {
		v.handle(this);
	}

}
