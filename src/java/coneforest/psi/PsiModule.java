package coneforest.psi;

public class PsiModule
	extends PsiDictionary
{
	protected void registerOperatorClasses(Class<? extends PsiOperator>... operatorClasses)
		throws PsiException
	{
		try
		{
			for(Class<? extends PsiOperator> operatorClass: operatorClasses)
			{
				PsiOperator operator=operatorClass.newInstance();
				put(operator.getName(), operator);
			}
		}
		catch(InstantiationException|IllegalAccessException e)
		{
			throw new PsiException("invalidexternal");
		}
	}
}
