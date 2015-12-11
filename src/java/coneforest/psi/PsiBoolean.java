package coneforest.psi;

/**
 *	A representation of Ψ-{@code boolean} object.
 */
public class PsiBoolean
	implements
		PsiAtomic,
		PsiLogical<PsiBoolean>
{
	private PsiBoolean()
	{
	}

	/**
	 *	@return a string {@code "boolean"}.
	 */
	@Override
	public String getTypeName()
	{
		return "boolean";
	}

	/**
	 *	Returns a boolean value of this object.
	 *
	 *	@return a boolean value of this object.
	 */
	public boolean booleanValue()
	{
		return this==TRUE;
	}

	/**
	 *	@return a string {@code false} or {@code true} depending on this object
	 *	value.
	 */
	@Override
	public String toSyntaxString()
	{
		return ""+booleanValue();
	}

	/**
	 *	Returns a result of boolean negation of this object.
	 *
	 *	@return a result.
	 */
	@Override
	public PsiBoolean psiNot()
	{
		return booleanValue()? FALSE: TRUE;
	}

	/**
	 *	Returns a result of boolean disjunction of this object and given
	 *	object.
	 *
	 *	@param oBoolean given object.
	 *	@return a result.
	 */
	@Override
	public PsiBoolean psiOr(final PsiBoolean oBoolean)
	{
		return PsiBoolean.valueOf(booleanValue() || oBoolean.booleanValue());
	}

	/**
	 *	Returns a result of boolean conjunction of this object and given
	 *	object.
	 *
	 *	@param oBoolean given object.
	 *	@return a result.
	 */
	@Override
	public PsiBoolean psiAnd(final PsiBoolean oBoolean)
	{
		return PsiBoolean.valueOf(booleanValue() && oBoolean.booleanValue());
	}

	/**
	 *	Returns a result of boolean exclusive disjunction of this object and
	 *	given object.
	 *
	 *	@param oBoolean given object.
	 *	@return a result.
	 */
	@Override
	public PsiBoolean psiXor(final PsiBoolean oBoolean)
	{
		return PsiBoolean.valueOf(booleanValue() ^ oBoolean.booleanValue());
	}

	/**
	 *	Returns a result of equality test of this object and given object. 
	 *
	 *	@return a result.
	 */
	@Override
	public PsiBoolean psiEq(final PsiObject obj)
	{
		return PsiBoolean.valueOf(equals(obj));
	}

	@Override
	public int hashCode()
	{
		return booleanValue()? 1231: 1237;
	}

	@Override
	public boolean equals(final Object object)
	{
		return object instanceof PsiBoolean
				&& this==object;
	}

	/**
	 *	Returns a Ψ-{@code boolean} representing the given value.
	 *
	 *	@param oBoolean a given value.
	 *	@return a Ψ-{@code boolean} object.
	 */
	public static PsiBoolean valueOf(final boolean oBoolean)
	{
		return oBoolean? TRUE: FALSE;
	}

	/**
	 *	A Ψ-{@code boolean} constant, representing false.
	 */
	public static final PsiBoolean FALSE=new PsiBoolean();

	/**
	 *	A Ψ-{@code boolean} constant, representing true.
	 */
	public static final PsiBoolean TRUE=new PsiBoolean();
}
