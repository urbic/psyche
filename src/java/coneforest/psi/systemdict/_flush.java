package coneforest.psi.systemdict;
import coneforest.psi.*;

public class _flush extends PsiOperator
{
	@Override
	public void invoke(Interpreter interpreter)
	{
		OperandStack opstack=interpreter.getOperandStack();
		if(opstack.size()<1)
		{
			interpreter.error("stackunderflow");
			return;
		}

		PsiObject file=opstack.pop();
		try
		{
			((PsiFlushable)file).psiFlush();
		}
		catch(ClassCastException e)
		{
			opstack.push(file);
			interpreter.error("typecheck", this);
		}
		catch(PsiException e)
		{
			opstack.push(file);
			interpreter.error(e.kind(), this);
		}
	}
}
