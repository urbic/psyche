package coneforest.psi.core;
import coneforest.psi.*;

public class _type extends PsiOperator
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

		opstack.push(new PsiCommand(opstack.pop().getTypeName()+"type"));
	}
}
