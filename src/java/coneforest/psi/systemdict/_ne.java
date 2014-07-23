package coneforest.psi.systemdict;
import coneforest.psi.*;

public class _ne extends PsiOperator
{
	public void execute(Interpreter interpreter)
	{
		OperandStack opstack=interpreter.getOperandStack();
		if(opstack.size()<2)
		{
			interpreter.error("stackunderflow");
			return;
		}
		PsiObject obj2=opstack.pop();
		PsiObject obj1=opstack.pop();

		opstack.push(obj1.ne(obj2));
		/*
		if(obj1 instanceof PsiNumeric && obj2 instanceof PsiNumeric)
			opstack.push(PsiNumeric.eq((PsiNumeric)obj1, (PsiNumeric)obj2));
		else if(obj1 instanceof PsiStringlike && obj2 instanceof PsiStringlike)
			opstack.push(PsiStringlike.eq((PsiStringlike)obj1, (PsiStringlike)obj2));
		else
			interpreter.error("typecheck");
		*/
	}
}
