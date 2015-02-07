package coneforest.psi.core;
import coneforest.psi.*;

public class _tokens extends PsiOperator
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

		final PsiObject stringlike=opstack.pop();
		try
		{
			interpreter.interpretBraced(new PsiStringReader((PsiStringlike)stringlike));
		}
		catch(ClassCastException e)
		{
			opstack.push(stringlike);
			interpreter.handleError(e, this);
		}
	}
}
