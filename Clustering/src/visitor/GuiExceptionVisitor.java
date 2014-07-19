package visitor;

import exception.EmptyKFieldException;
import exception.MinGreaterMaxException;
import exception.NoAlgoSelectedException;
import exception.NoNegativeNumbersAllowedException;
import exception.NoPointsGeneratedException;
import exception.NoStopConditionSelectedException;
import exception.OnlyDigitsAllowedException;
import exception.WrongFileFormatException;

public interface GuiExceptionVisitor {

	public void handle(EmptyKFieldException e);
	public void handle(OnlyDigitsAllowedException e);
	public void handle(NoNegativeNumbersAllowedException e);
	public void handle(NoStopConditionSelectedException e);
	public void handle(NoPointsGeneratedException e);
	public void handle(NoAlgoSelectedException e);
	public void handle(WrongFileFormatException e);
	public void handle(MinGreaterMaxException e);
}
