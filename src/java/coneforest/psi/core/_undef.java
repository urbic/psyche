package coneforest.psi.core;
import coneforest.psi.*;

public class _undef extends PsiOperator
{
	@Override
	public void action(final Interpreter interpreter)
		throws ClassCastException, PsiException
	{
		final OperandStack opstack=interpreter.getOperandStack();
		final PsiObject[] ops=opstack.popOperands(2);
		((PsiDictionarylike)ops[0]).psiUndef((PsiStringlike)ops[1]);
	}
}
