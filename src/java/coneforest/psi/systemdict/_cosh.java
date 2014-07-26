package coneforest.psi.systemdict;
import coneforest.psi.*;

public class _cosh extends PsiOperator
{
	public void execute(Interpreter interpreter)
	{
		OperandStack opstack=interpreter.getOperandStack();
		if(opstack.size()<1)
		{
			interpreter.error("stackunderflow");
			return;
		}
		
		PsiObject numeric=opstack.pop();

		try
		{
			opstack.push(((PsiNumeric)numeric).cosh());
		}
		catch(ClassCastException e)
		{
			interpreter.error("typecheck");
		}
	}
}
