package coneforest.psi.systemdict;
import coneforest.psi.*;

public class _print extends PsiOperator
{
	@Override
	public void execute(Interpreter interpreter)
	{
		OperandStack opstack=interpreter.getOperandStack();
		if(opstack.size()<1)
		{
			interpreter.error("stackunderflow", this);
			return;
		}

		PsiObject string=opstack.pop();
		try
		{
			((PsiWriter)interpreter.getSystemDictionary().psiGet(new PsiName("stdout")))
				.psiWriteString((PsiString)string);
		}
		catch(ClassCastException e)
		{
			opstack.push(string);
			interpreter.error("typecheck", this);
		}
		catch(PsiException e)
		{
			opstack.push(string);
			interpreter.error(e.kind(), this);
		}
	}
}
