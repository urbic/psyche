package coneforest.psi;

public class PsiSecurityErrorException
	extends PsiException
{
	@Override
	public String getName()
	{
		return NAME;
	}

	public final String NAME="securityerror";
}
