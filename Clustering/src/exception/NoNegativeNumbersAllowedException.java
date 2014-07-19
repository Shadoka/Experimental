package exception;

import javax.swing.JTextField;

import visitor.GuiExceptionVisitor;

import constants.ExceptionConstants;

public class NoNegativeNumbersAllowedException extends AbstractGuiException {

	private static final long serialVersionUID = 2746901399364681164L;
	private final JTextField txtField;
	
	public NoNegativeNumbersAllowedException(JTextField txtField) {
		super(ExceptionConstants.NoNegativeNumbersAllowedMessage);
		this.txtField = txtField;
	}

	public JTextField getTxtField() {
		return txtField;
	}

	@Override
	public void accept(GuiExceptionVisitor v) {
		v.handle(this);
	}

}
