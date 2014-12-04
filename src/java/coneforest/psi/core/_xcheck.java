package coneforest.psi.core;
import coneforest.psi.*;

public class _xcheck extends PsiOperator
{
	@Override
	public void invoke(final Interpreter interpreter)
	{
		final OperandStack opstack=interpreter.getOperandStack();
		if(opstack.size()<1)
		{
			interpreter.error("stackunderflow", this);
			return;
		}
		opstack.push(new PsiBoolean(opstack.pop().isExecutable()));
	}
}
