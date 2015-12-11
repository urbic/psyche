package coneforest.psi.core;
import coneforest.psi.*;

public final class _slice extends PsiOperator
{
	@Override
	public void action(final Interpreter interpreter)
		throws ClassCastException, PsiException
	{
		final OperandStack ostack=interpreter.operandStack();
		final PsiObject[] ops=ostack.popOperands(2);
		ostack.push(((PsiIndexed)ops[0]).psiSlice((PsiIterable)ops[1]));
	}
}
