package coneforest.psi.systemdict;
import coneforest.psi.*;

public class _currentdict extends PsiOperator
{
	@Override
	public void invoke(Interpreter interpreter)
	{
		interpreter.getOperandStack().push(interpreter.getCurrentDictionary());
	}
}
