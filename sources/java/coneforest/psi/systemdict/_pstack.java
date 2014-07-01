package coneforest.psi.systemdict;
import coneforest.psi.*;

public class _pstack extends PsiOperator
{
	public void execute(Interpreter interpreter)
	{
		OperandStack opstack=interpreter.getOperandStack();
		// TODO reverse order
		for(PsiObject obj: opstack)
			System.out.print(obj+" ");
	}
}
