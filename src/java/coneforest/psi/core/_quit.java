package coneforest.psi.core;
import coneforest.psi.*;

public class _quit extends PsiOperator
{
	@Override
	public void action(final Interpreter interpreter)
	{
		System.exit(0);
	}
}
