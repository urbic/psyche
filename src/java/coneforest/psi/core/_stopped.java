package coneforest.psi.core;
import coneforest.psi.*;

public class _stopped extends PsiOperator
{
	@Override
	public void action(final Interpreter interpreter)
		throws PsiException
	{
		final OperandStack opstack=interpreter.getOperandStack();
		PsiObject obj=opstack.popOperands(1)[0];
		final int stopLevel=interpreter.pushStopLevel();
		obj.invoke(interpreter);
		interpreter.handleExecutionStack(stopLevel);
		opstack.push(new PsiBoolean(interpreter.getStopFlag()));
		interpreter.setStopFlag(false);
		interpreter.popStopLevel();
	}
}
