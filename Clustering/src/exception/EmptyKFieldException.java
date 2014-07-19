package exception;

import visitor.GuiExceptionVisitor;
import constants.ExceptionConstants;

public class EmptyKFieldException extends AbstractGuiException {

	private static final long serialVersionUID = 8514301972060988779L;

	public EmptyKFieldException() {
		super(ExceptionConstants.EmptyKFieldMessage);
	}

	@Override
	public void accept(GuiExceptionVisitor v) {
		v.handle(this);
	}

}
