package coneforest.psi.core;
import coneforest.psi.*;

public class _lock extends PsiOperator
{
	@Override
	public void action(final Interpreter interpreter)
	{
		interpreter.getOperandStack().push(new PsiLock());
	}
}
