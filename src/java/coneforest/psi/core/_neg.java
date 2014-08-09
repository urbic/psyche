package coneforest.psi.core;
import coneforest.psi.*;

public class _neg extends PsiOperator
{
	@Override
	public void invoke(Interpreter interpreter)
	{
		OperandStack opstack=interpreter.getOperandStack();
		if(opstack.size()<1)
		{
			interpreter.error("stackunderflow", this);
			return;
		}

		PsiObject arihmetic=opstack.pop();
		try
		{
			opstack.push((PsiObject)((PsiArithmetic)arihmetic).psiNeg());
		}
		catch(Exception e)
		{
			opstack.push(arihmetic);
			interpreter.error(e, this);
		}
	}
}
