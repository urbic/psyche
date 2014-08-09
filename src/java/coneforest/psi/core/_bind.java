package coneforest.psi.core;
import coneforest.psi.*;

public class _bind extends PsiOperator
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

		PsiObject array=opstack.peek();
		try
		{
			bind((PsiArray)array, interpreter.getDictionaryStack());
		}
		catch(Exception e)
		{
			opstack.push(array);
			interpreter.error(e, this);
		}
	}

	private static void bind(PsiArray array, DictionaryStack dictstack)
		throws PsiException
	{
		bindHelper(array, new java.util.HashSet<PsiArray>(), dictstack);
	}

	private static void bindHelper(PsiArray array, java.util.HashSet<PsiArray> bound, DictionaryStack dictstack)
		throws PsiException
	{
		for(int i=0; i<array.length(); i++)
		{
			PsiObject obj=array.psiGet(i);
			if(obj instanceof PsiArray && bound.add((PsiArray)obj))
				bindHelper((PsiArray)obj, bound, dictstack);
			else if(obj instanceof PsiName && ((PsiName)obj).isExecutable())
			{
				PsiObject value=dictstack.load((PsiName)obj);
				if(value instanceof PsiOperator)
					array.psiPut(i, value);
			}
		}
	}
}
