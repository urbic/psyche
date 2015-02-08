package coneforest.psi.core;
import coneforest.psi.*;

public class _writestring extends PsiOperator
{
	@Override
	public void action(final Interpreter interpreter)
		throws ClassCastException, PsiException
	{
		final OperandStack opstack=interpreter.getOperandStack();
		final PsiObject[] ops=opstack.popOperands(2);
		((PsiWriter)ops[0]).psiWriteString((PsiStringlike)ops[1]);
	}
}
