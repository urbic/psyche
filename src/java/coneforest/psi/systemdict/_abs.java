package coneforest.psi.systemdict;
import coneforest.psi.*;

public class _abs extends PsiOperator
{
	public void execute(Interpreter interpreter)
	{
		OperandStack opstack=interpreter.getOperandStack();
		if(opstack.size()<1)
		{
			interpreter.error("stackunderflow", this);
			return;
		}
		
		PsiObject arithmetic=opstack.pop();
		try
		{
			//opstack.push((PsiObject)((PsiArithmetic)arithmetic).abs());
			opstack.push(((PsiNumeric)arithmetic).abs());
		}
		catch(ClassCastException e)
		{
			interpreter.error("typecheck", this);
		}
		/*
		if(obj instanceof PsiNumeric)
			opstack.push(PsiNumeric.abs((PsiNumeric)obj));
		else
		{
			opstack.push(obj);
			interpreter.error("typecheck");
		}
		*/
	}
}
