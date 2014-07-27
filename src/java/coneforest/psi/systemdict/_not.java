package coneforest.psi.systemdict;
import coneforest.psi.*;

public class _not extends PsiOperator
{
	public void execute(Interpreter interpreter)
	{
		OperandStack opstack=interpreter.getOperandStack();
		if(opstack.size()<1)
		{
			interpreter.error("stackunderflow", this);
			return;
		}

		PsiObject logical=opstack.pop();
		try
		{
			opstack.push((PsiObject)((PsiLogical)logical).not());
		}
		catch(ClassCastException e)
		{
			interpreter.error("typecheck", this);
		}
	}
}
