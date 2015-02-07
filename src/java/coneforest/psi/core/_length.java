package coneforest.psi.core;
import coneforest.psi.*;

public class _length extends PsiOperator
{
	@Override
	public void invoke(final Interpreter interpreter)
	{
		final OperandStack opstack=interpreter.getOperandStack();
		if(opstack.size()<1)
		{
			interpreter.handleError("stackunderflow", this);
			return;
		}

		final PsiObject lengthy=opstack.pop();
		try
		{
			opstack.push(((PsiLengthy)lengthy).psiLength());
		}
		catch(ClassCastException e)
		{
			opstack.push(lengthy);
			interpreter.handleError(e, this);
		}
	}
}
