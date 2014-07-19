package exception;

import visitor.GuiExceptionVisitor;

public abstract class AbstractGuiException extends Exception {

	private static final long serialVersionUID = 5955589712898673000L;
	
	protected AbstractGuiException(String message) {
		super(message);
	}
	
	public abstract void accept(GuiExceptionVisitor v);
	
}
