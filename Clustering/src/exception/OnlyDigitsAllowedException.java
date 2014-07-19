package exception;

import javax.swing.JTextField;

import visitor.GuiExceptionVisitor;

import constants.ExceptionConstants;

public class OnlyDigitsAllowedException extends AbstractGuiException {

	private static final long serialVersionUID = 1760313011381928732L;
	private final JTextField txtField;
	
	public OnlyDigitsAllowedException(JTextField txtField) {
		super(ExceptionConstants.OnlyDigitsAllowedMessage);
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
