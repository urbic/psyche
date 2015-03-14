package coneforest.psi.core;
import coneforest.psi.*;

public class _notify extends PsiOperator
{
	@Override
	public void action(final Interpreter interpreter)
		throws ClassCastException, PsiException
	{
		final OperandStack opstack=interpreter.getOperandStack();
		((PsiCondition)opstack.popOperands(1)[0]).psiNotify();
	}
}
