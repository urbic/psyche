package coneforest.psi.core;
import coneforest.psi.*;

public class _fileexists extends PsiOperator
{
	@Override
	public void action(final Interpreter interpreter)
		throws ClassCastException, PsiException
	{
		final OperandStack opstack=interpreter.getOperandStack();
		String name=Utility.fileNameToNative(((PsiStringlike)opstack.popOperands(1)[0]).getString());
		try
		{
			opstack.push(PsiBoolean.valueOf((new java.io.File(name)).exists()));
		}
		catch(SecurityException e)
		{
			throw new PsiException("security");
		}
	}
}
