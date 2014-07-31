package coneforest.psi.systemdict;
import coneforest.psi.*;

public class _println extends PsiOperator
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
			PsiWriter stdwriter=(PsiWriter)interpreter.getSystemDictionary().psiGet(new PsiName("stdout"));
			stdwriter.psiWriteString((PsiString)string);
			stdwriter.psiWrite(new PsiInteger(10));
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
