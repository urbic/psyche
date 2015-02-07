package coneforest.psi.core;
import coneforest.psi.*;

public class _setseed extends PsiOperator
{
	@Override
	public void invoke(final Interpreter interpreter)
	{
		final OperandStack opstack=interpreter.getOperandStack();
		if(opstack.size()<2)
		{
			interpreter.handleError("stackunderflow", this);
			return;
		}

		final PsiObject integer=opstack.pop();
		final PsiObject random=opstack.pop();
		try
		{
			((PsiRandom)random).psiSetSeed((PsiInteger)integer);
		}
		catch(ClassCastException e)
		{
			opstack.push(random);
			opstack.push(integer);
			interpreter.handleError(e, this);
		}
	}
}
