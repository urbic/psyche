package coneforest.psi.core;
import coneforest.psi.*;

public final class _print extends PsiOperator
{
	@Override
	public void action(final Interpreter interpreter)
		throws ClassCastException, PsiException
	{
		final OperandStack opstack=interpreter.getOperandStack();
		((PsiWriter)interpreter.getDictStack().load("stdout"))
			.psiWriteString((PsiStringy)opstack.popOperands(1)[0]);
	}
}
