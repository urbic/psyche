package coneforest.psi.core;
import coneforest.psi.*;

public final class _prepend extends PsiOperator
{
	@Override
	public void action(final Interpreter interpreter)
		throws ClassCastException, PsiException
	{
		final PsiObject[] ops=interpreter.operandStack().popOperands(2);
		((PsiPrependable)ops[0]).psiPrepend(ops[1]);
	}
}
