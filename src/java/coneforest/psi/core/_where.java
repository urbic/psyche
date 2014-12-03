package coneforest.psi.core;
import coneforest.psi.*;

public class _where extends PsiOperator
{
	@Override
	public void invoke(Interpreter interpreter)
	{
		OperandStack opstack=interpreter.getOperandStack();
		if(opstack.size()<1)
		{
			interpreter.error("stackunderflow", this);
			return;
		}

		PsiObject key=opstack.pop();
		try
		{
			PsiDictionarylike dict=interpreter.getDictionaryStack().where((PsiStringlike)key);
			if(dict!=null)
				opstack.push((PsiObject)dict);
			opstack.push(new PsiBoolean(dict!=null));
		}
		catch(ClassCastException e)
		{
			opstack.push(key);
			interpreter.error(e, this);
		}
	}
}
