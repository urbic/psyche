package coneforest.psi.core;
import coneforest.psi.*;

public class _remove extends PsiOperator
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

		final PsiObject obj=opstack.pop();
		final PsiObject setlike=opstack.pop();
		try
		{
			((PsiSetlike)setlike).psiRemove(obj);
		}
		catch(ClassCastException e)
		{
			opstack.push(setlike);
			opstack.push(obj);
			interpreter.handleError(e, this);
		}
	}
}
