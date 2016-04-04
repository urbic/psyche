package coneforest.psi.core;
import coneforest.psi.*;

public final class _unite extends PsiOperator
{
	@Override
	public void action(final Interpreter interpreter)
		throws ClassCastException, PsiException
	{
		final OperandStack ostack=interpreter.operandStack();
		final PsiObject[] ops=ostack.popOperands(2);
		ostack.push(((PsiIterable)ops[0]).psiUnite((PsiStringy)ops[1]));
	}
}
