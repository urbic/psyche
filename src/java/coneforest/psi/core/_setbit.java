package coneforest.psi.core;
import coneforest.psi.*;

public final class _setbit extends PsiOperator
{
	@Override
	public void action(final Interpreter interpreter)
		throws ClassCastException, PsiException
	{
		final OperandStack ostack=interpreter.operandStack();
		final PsiObject[] ops=ostack.popOperands(2);
		ostack.push(((PsiBitwise)ops[0]).psiSetBit((PsiInteger)ops[1]));
	}
}
