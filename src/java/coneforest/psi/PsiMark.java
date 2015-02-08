package coneforest.psi;

/**
 *	A representation of Ψ <code class="type">mark</code> object.
 */
public final class PsiMark
	extends PsiAbstractObject
	implements PsiAtomic
{
	private PsiMark()
	{
	}

	public String getTypeName() { return "mark"; }

	public String toString()
	{
		return "-mark-";
	}

	@Override
	public PsiBoolean psiEq(final PsiObject obj)
	{
		return PsiBoolean.valueOf(obj==MARK);
	}

	public static final PsiMark MARK=new PsiMark();
}
