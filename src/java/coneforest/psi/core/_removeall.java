package coneforest.psi.core;
import coneforest.psi.*;

public class _removeall extends PsiOperator
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

		final PsiObject iterable=opstack.pop();
		final PsiObject setlike=opstack.pop();
		try
		{
			((PsiSetlike)setlike).psiRemoveAll((PsiIterable)iterable);
		}
		catch(ClassCastException e)
		{
			opstack.push(setlike);
			opstack.push(iterable);
			interpreter.handleError(e, this);
		}
	}
}
