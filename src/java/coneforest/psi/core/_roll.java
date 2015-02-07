package coneforest.psi.core;
import coneforest.psi.*;

public class _roll extends PsiOperator
{
	@Override
	public void invoke(final Interpreter interpreter)
	{
		final OperandStack opstack=interpreter.getOperandStack();

		if(opstack.size()<3)
		{
			interpreter.handleError("stackunderflow", this);
			return;
		}

		final PsiObject j=opstack.pop();
		final PsiObject n=opstack.pop();
		try
		{
			int nValue=((PsiInteger)n).intValue();
			int jValue=((PsiInteger)j).intValue();
			int opstackSize=opstack.size();
			if(nValue<0)
				throw new PsiException("rangecheck");
			else if(opstackSize<nValue)
				throw new PsiException("stackunderflow");
			else
			{
				while(jValue<0)
					jValue+=nValue;
				jValue%=nValue;
				for(int i=0; i<jValue; i++)
					opstack.add(opstackSize-nValue, opstack.pop());
			}
		}
		catch(ClassCastException|PsiException e)
		{
			opstack.push(n);
			opstack.push(j);
			interpreter.handleError(e, this);
		}
	}
}
