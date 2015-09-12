package coneforest.psi.core;
import coneforest.psi.*;

public final class _stop extends PsiOperator
{
	@Override
	public void action(final Interpreter interpreter)
	{
		interpreter.setStopFlag(true);
		if(interpreter.currentStopLevel()!=-1)
			interpreter.getExecutionStack().setSize(interpreter.currentStopLevel());
		else
			interpreter.quit();
	}
}
