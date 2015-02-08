package coneforest.psi.core;
import coneforest.psi.*;

public class _readline extends PsiOperator
{
	@Override
	public void action(final Interpreter interpreter)
		throws ClassCastException, PsiException
	{
		final OperandStack opstack=interpreter.getOperandStack();
		PsiStringlike eol=(PsiStringlike)interpreter.getDictionaryStack().load("eol");
		PsiString string=((PsiReadable)opstack.popOperands(1)[0]).psiReadLine(eol);
		if(string.length()>0)
			opstack.push(string);
		opstack.push(new PsiBoolean(string.length()>0));
	}
}
