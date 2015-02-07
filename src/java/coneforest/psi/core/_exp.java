package coneforest.psi.core;
import coneforest.psi.*;

public class _exp extends PsiOperator
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

		final PsiObject cn=opstack.pop();
		try
		{
			opstack.push(((PsiComplexNumeric)cn).psiExp());
		}
		catch(ClassCastException e)
		{
			opstack.push(cn);
			interpreter.handleError(e, this);
		}
	}
}
