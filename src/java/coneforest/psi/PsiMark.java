package coneforest.psi;

public final class PsiMark
	extends PsiObject
	implements PsiAtomic
{
	public String getTypeName() { return "mark"; }

	public String toString()
	{
		return "-mark-";
	}

	@Override
	public PsiBoolean psiEq(final PsiObject obj)
	{
		return new PsiBoolean(obj instanceof PsiMark);
	}
}
