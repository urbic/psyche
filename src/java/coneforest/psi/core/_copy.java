package coneforest.psi.core;
import coneforest.psi.*;

public class _copy extends PsiOperator
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

		final PsiObject n=opstack.pop();
		try
		{
			int nValue=((PsiInteger)n).intValue();
			if(nValue<0)
				interpreter.error("rangecheck", this);
			else if(opstack.size()<nValue)
				interpreter.error("stackunderflow", this);
			else
			{
				int opsize=opstack.size();
				for(int j=opsize-nValue; j<opsize; j++)
					opstack.push(opstack.get(j));
			}
		}
		// TODO other types of topmost operands?
		catch(ClassCastException e)
		{
			opstack.push(n);
			interpreter.error(e, this);
		}
	}
}
