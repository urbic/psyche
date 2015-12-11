package coneforest.psi.core;
import coneforest.psi.*;

public final class _ge extends PsiOperator
{
	@Override
	public void action(final Interpreter interpreter)
		throws ClassCastException, PsiException
	{
		final OperandStack ostack=interpreter.operandStack();
		final PsiObject[] ops=ostack.popOperands(2);
		ostack.push(((PsiScalar)ops[0]).psiGe((PsiScalar)ops[1]));
	}
}
