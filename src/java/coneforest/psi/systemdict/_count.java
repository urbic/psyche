package coneforest.psi.systemdict;
import coneforest.psi.*;

public class _count extends PsiOperator
{
	@Override
	public void execute(Interpreter interpreter)
	{
		OperandStack opstack=interpreter.getOperandStack();
		opstack.push(new PsiInteger(opstack.size()));
	}
}
