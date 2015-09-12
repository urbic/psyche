package coneforest.psi.core;
import coneforest.psi.*;

public final class _get extends PsiOperator
{
	@Override
	public void action(final Interpreter interpreter)
		throws ClassCastException, PsiException
	{
		final OperandStack opstack=interpreter.getOperandStack();
		final PsiObject[] ops=opstack.popOperands(2);
		opstack.push(((PsiIndexed)ops[0]).psiGet(ops[1]));
	}
}
