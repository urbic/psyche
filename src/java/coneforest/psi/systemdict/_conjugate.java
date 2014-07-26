package coneforest.psi.systemdict;
import coneforest.psi.*;

public class _conjugate extends PsiOperator
{
	public void execute(Interpreter interpreter)
	{
		OperandStack opstack=interpreter.getOperandStack();
		if(opstack.size()<1)
		{
			interpreter.error("stackunderflow");
			return;
		}

		PsiObject cn=opstack.pop();

		try
		{
			opstack.push(((PsiComplexNumeric)cn).conjugate());
		}
		catch(ClassCastException e)
		{
			opstack.push(cn);
			interpreter.error("typecheck");
		}
	}
}
