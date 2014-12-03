package coneforest.psi.core;
import coneforest.psi.*;

public class _null extends PsiOperator
{
	@Override
	public void invoke(final Interpreter interpreter)
	{
		interpreter.getOperandStack().push(new PsiNull());
	}
}
