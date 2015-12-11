package coneforest.psi;

/**
 *	A representation of Ψ-{@code boolean} object.
 */
public class PsiBoolean
	implements
		PsiAtomic,
		PsiLogical<PsiBoolean>
{
	private PsiBoolean(final boolean value)
	{
		this.value=value;
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
		return value;
	}

	/**
	 *	@return a string {@code false} or {@code true} depending on this object
	 *	value.
	 */
	@Override
	public String toSyntaxString()
	{
		return ""+value;
	}

	/**
	 *	Returns a result of boolean negation of this object.
	 *
	 *	@return a result.
	 */
	@Override
	public PsiBoolean psiNot()
	{
		return PsiBoolean.valueOf(!value);
	}

	/**
	 *	Returns a result of boolean disjunction of this object and given
	 *	object.
	 *
	 *	@param bool given object.
	 *	@return a result.
	 */
	@Override
	public PsiBoolean psiOr(final PsiBoolean bool)
	{
		return PsiBoolean.valueOf(value || bool.value);
	}

	/**
	 *	Returns a result of boolean conjunction of this object and given
	 *	object.
	 *
	 *	@param bool given object.
	 *	@return a result.
	 */
	@Override
	public PsiBoolean psiAnd(final PsiBoolean bool)
	{
		return PsiBoolean.valueOf(value && bool.value);
	}

	/**
	 *	Returns a result of boolean exclusive disjunction of this object and
	 *	given object.
	 *
	 *	@param bool given object.
	 *	@return a result.
	 */
	@Override
	public PsiBoolean psiXor(final PsiBoolean bool)
	{
		return PsiBoolean.valueOf(value ^ bool.value);
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
		return value? 1231: 1237;
	}

	@Override
	public boolean equals(final Object object)
	{
		return object instanceof PsiBoolean
				&& value==((PsiBoolean)object).value;
	}

	/**
	 *	Returns a Ψ-{@code boolean} representing the given value.
	 *
	 *	@param bool a given value.
	 *	@return a Ψ-{@code boolean} object.
	 */
	public static PsiBoolean valueOf(final boolean bool)
	{
		return bool? TRUE: FALSE;
	}

	/**
	 *	A Ψ-{@code boolean} constant, representing false.
	 */
	public static final PsiBoolean FALSE=new PsiBoolean(false);

	/**
	 *	A Ψ-{@code boolean} constant, representing true.
	 */
	public static final PsiBoolean TRUE=new PsiBoolean(true);

	private final boolean value;
}
