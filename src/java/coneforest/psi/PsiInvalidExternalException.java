package coneforest.psi;

public class PsiInvalidExternalException
	extends PsiException
{
	@Override
	public String getName()
	{
		return NAME;
	}

	public final String NAME="invalidexternal";
}
