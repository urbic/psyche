package coneforest.psi.systemdict;
import coneforest.psi.*;

public class _countexecstack extends PsiOperator
{
	@Override
	public void invoke(Interpreter interpreter)
	{
		interpreter.getOperandStack().push(new PsiInteger(interpreter.getExecutionStack().size()));
	}
}
