package coneforest.psi.core;
import coneforest.psi.*;

public class _counttomark extends PsiOperator
{
	@Override
	public void action(final Interpreter interpreter)
		throws PsiException
	{
		final OperandStack opstack=interpreter.getOperandStack();
		for(int i=opstack.size()-1; i>=0; i--)
			if(opstack.get(i) instanceof PsiMark)
			{
				opstack.push(PsiInteger.valueOf(opstack.size()-1-i));
				return;
			}
		throw new PsiUnmatchedMarkException();
	}
}
