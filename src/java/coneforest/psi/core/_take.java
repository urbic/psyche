package coneforest.psi.core;
import coneforest.psi.*;

public final class _take extends PsiOperator
{
	@Override
	public void action(final Interpreter interpreter)
		throws ClassCastException, PsiException
	{
		final OperandStack ostack=interpreter.operandStack();
		ostack.push(((PsiQueuelike)ostack.popOperands(1)[0]).psiTake());
	}
}
