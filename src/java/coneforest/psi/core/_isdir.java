package coneforest.psi.core;
import coneforest.psi.*;

public class _isdir extends PsiOperator
{
	@Override
	public void action(final Interpreter interpreter)
		throws ClassCastException, PsiException
	{
		final OperandStack opstack=interpreter.getOperandStack();
		String name=Utility.fileNameToNative(((PsiStringlike)opstack.popOperands(1)[0]).getString());
		try
		{
			opstack.push(new PsiBoolean((new java.io.File(name)).isDirectory()));
		}
		catch(SecurityException e)
		{
			throw new PsiException("security");
		}
	}
}
