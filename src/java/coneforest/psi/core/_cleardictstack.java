package coneforest.psi.core;
import coneforest.psi.*;

public class _cleardictstack extends PsiOperator
{
	@Override
	public void invoke(final Interpreter interpreter)
	{
		interpreter.getDictionaryStack().setSize(3);
	}
}
