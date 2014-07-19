package exception;

import javax.swing.JTextField;

import visitor.GuiExceptionVisitor;

import constants.ExceptionConstants;

public class MinGreaterMaxException extends AbstractGuiException {

	private static final long serialVersionUID = -5129437991787895642L;
	private final JTextField txtField;
	
	public MinGreaterMaxException(JTextField txtField) {
		super(ExceptionConstants.MinGreaterMaxMessage);
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
