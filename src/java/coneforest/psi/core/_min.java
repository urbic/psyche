package coneforest.psi.core;
import coneforest.psi.*;

public class _min extends PsiOperator
{
	@Override
	public void action(final Interpreter interpreter)
		throws ClassCastException, PsiException
	{
		final OperandStack opstack=interpreter.getOperandStack();
		final PsiObject[] ops=opstack.popOperands(2);
		opstack.push(ops[((PsiScalar)ops[0]).psiLt((PsiScalar)ops[1]).booleanValue()? 0: 1]);
	}
}
