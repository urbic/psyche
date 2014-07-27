package coneforest.psi.systemdict;
import coneforest.psi.*;

public class _exec extends PsiOperator
{
	public void execute(Interpreter interpreter)
	{
		OperandStack opstack=interpreter.getOperandStack();
		if(opstack.size()<1)
		{
			interpreter.error("stackunderflow", this);
			return;
		}
		
		opstack.pop().invoke(interpreter);
	}
}
