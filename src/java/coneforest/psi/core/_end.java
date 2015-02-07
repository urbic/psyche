package coneforest.psi.core;
import coneforest.psi.*;

public class _end extends PsiOperator
{
	@Override
	public void invoke(final Interpreter interpreter)
	{
		DictionaryStack dictstack=interpreter.getDictionaryStack();
		if(dictstack.size()<=3)
			interpreter.handleError("dictstackunderflow", this);
		else
			dictstack.pop();
	}
}
