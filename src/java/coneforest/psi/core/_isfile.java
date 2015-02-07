package coneforest.psi.core;
import coneforest.psi.*;

public class _isfile extends PsiOperator
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
			String name=Utility.fileNameToNative(((PsiStringlike)stringlike).getString());
			try
			{
				opstack.push(new PsiBoolean((new java.io.File(name)).isFile()));
			}
			catch(SecurityException e)
			{
				throw new PsiException("security");
			}
		}
		catch(ClassCastException|PsiException e)
		{
			opstack.push(stringlike);
			interpreter.handleError(e, this);
		}
	}
}
