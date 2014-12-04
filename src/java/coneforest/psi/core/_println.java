package coneforest.psi.core;
import coneforest.psi.*;

public class _println extends PsiOperator
{
	@Override
	public void invoke(final Interpreter interpreter)
	{
		final OperandStack opstack=interpreter.getOperandStack();
		if(opstack.size()<1)
		{
			interpreter.error("stackunderflow", this);
			return;
		}

		final PsiObject stringlike=opstack.pop();
		try
		{
			PsiWriter stdwriter=(PsiWriter)interpreter.getSystemDictionary().psiGet("stdout");
			stdwriter.psiWriteString((PsiStringlike)stringlike);
			stdwriter.psiWriteString((PsiStringlike)interpreter.getSystemDictionary().psiGet("eol"));
		}
		catch(ClassCastException|PsiException e)
		{
			opstack.push(stringlike);
			interpreter.error(e, this);
		}
	}
}
