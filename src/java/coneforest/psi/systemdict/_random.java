package coneforest.psi.systemdict;
import coneforest.psi.*;

public class _random extends PsiOperator
{
	public void execute(Interpreter interpreter)
	{
		interpreter.getOperandStack().push(new PsiRandom());
	}
}
